package group8.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.GameKeys;
import group8.common.data.World;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import static group8.core.main.Game.texture;
import group8.core.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

public class Game implements ApplicationListener {

    private static final String MAP_IMG = "Images/BaseMap/GrassField.png";
    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private World world = new World();
    private List<IGamePluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IGamePluginService> result;
    public static Texture texture;
    private static Texture texture1;
    public static Sprite sprite;
    private SpriteBatch spriteBatch;

    private HashMap<String,Texture> textureMap;

    private ArrayList<SpriteBatch> mapObjects;

    @Override
    public void create() {

        this.gameData.setDisplayHeight(Gdx.graphics.getHeight());
        this.gameData.setDisplayWidth(Gdx.graphics.getWidth());

        this.cam = new OrthographicCamera(this.gameData.getDisplayHeight() * 1.2f, this.gameData.getDisplayWidth() * 1.2f);

        this.sr = new ShapeRenderer();
        Gdx.input.setInputProcessor(new GameInputProcessor(this.gameData));

        this.result = this.lookup.lookupResult(IGamePluginService.class);
        this.result.addLookupListener(lookupListener);
        this.result.allItems();
        this.textureMap = new HashMap();
        
        spriteBatch = new SpriteBatch();
        texture1 = new Texture(Gdx.files.internal(MAP_IMG));

        sprite = new Sprite(texture1);

        for (IGamePluginService plugin : result.allInstances()) {
                plugin.start(gameData, world);
                gamePlugins.add(plugin);
            
        }
        
        this.loadTextures();
    }

    public void renderBackground() {
        this.spriteBatch.begin();
        sprite.draw(spriteBatch);
        this.spriteBatch.end();
    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.sr.setProjectionMatrix(cam.combined);
        this.spriteBatch.setProjectionMatrix(cam.combined);

        this.renderBackground();

        for (Entity e : world.getEntities()) {
            PositionPart part = e.getPart(PositionPart.class);
            //this.drawShapes(e);
            if (e.getImagePath() != null) {
                this.drawImg(e, part);
            }
            if (e.getType() == EntityType.PLAYER) {
                this.setCamFollowPlayer(part);
            }
            
            //TEST FOR ENEMY WAVE
            boolean hasRemoved = false;
            if (e.getType() == EntityType.ZOMBIE && gameData.getKeys().isDown(GameKeys.ENTER) && !hasRemoved){
                world.removeEntity(e);
                hasRemoved = true;
                
            }
            //END ENEMY WAVE TEST!
        }
        

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }

    }

    private void drawShapes(Entity entity) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {
                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }
            sr.end();
    }

    private void drawImg(Entity entity, PositionPart part) {
        this.spriteBatch.begin();
        if (!this.textureMap.containsKey(entity.getImagePath()) && entity.getImagePath() != null) {
            this.textureMap.put(entity.getImagePath(), new Texture(Gdx.files.internal(entity.getImagePath())));
        }
        this.texture = this.textureMap.get(entity.getImagePath());
        spriteBatch.draw(this.texture, part.getX(), part.getY(), entity.getWidth(), entity.getHeight());
        this.spriteBatch.end();
    }
    
    
    private void loadTextures() {
        for (Entity entity : world.getEntities()) {
            this.texture = new Texture(Gdx.files.internal(entity.getImagePath()));
            this.textureMap.put(entity.getImagePath(), texture);
        }
    }
    
    private void setCamFollowPlayer(PositionPart part) {
        cam.position.set(part.getX(), part.getY(),0);
        cam.update();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        this.textureMap.clear();
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return lookup.lookupAll(IEntityProcessingService.class);
    }

    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IGamePluginService> updated = result.allInstances();

            for (IGamePluginService us : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(us)) {
                        us.start(gameData, world);
                        gamePlugins.add(us);
                }
            }
            // Stop and remove module
            for (IGamePluginService gs : gamePlugins) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData, world);
                    gamePlugins.remove(gs);
                }
            }
        }

    };
}
