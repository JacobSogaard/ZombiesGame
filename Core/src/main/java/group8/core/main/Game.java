package group8.core.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;

import group8.core.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private World world = new World();
    private List<IGamePluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IGamePluginService> result;
    public static Texture texture;
    public static Sprite sprite;
    private SpriteBatch spriteBatch;
    private ArrayList<SpriteBatch> mapObjects;

    @Override
    public void create() {
        this.gameData.setDisplayHeight(Gdx.graphics.getHeight());
        this.gameData.setDisplayWidth(Gdx.graphics.getWidth());
        this.cam = new OrthographicCamera(this.gameData.getDisplayHeight(), this.gameData.getDisplayWidth());
        this.cam.update();
        this.sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(this.gameData));

        this.result = this.lookup.lookupResult(IGamePluginService.class);
        this.result.addLookupListener(lookupListener);
        this.result.allItems();

        texture = new Texture(Gdx.files.internal("pony.gif"));
        spriteBatch = new SpriteBatch();
        sprite = new Sprite(texture);

        for (IGamePluginService plugin : result.allInstances()) {
            plugin.start(gameData, world);
            gamePlugins.add(plugin);
        }
    }

    public void renderBackground() {
        sprite.draw(spriteBatch);
    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        this.sr.setProjectionMatrix(cam.combined);
        this.spriteBatch.setProjectionMatrix(cam.combined);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();

        spriteBatch.begin();
        for (Entity entity : world.getEntities()) {
            PositionPart part = entity.getPart(PositionPart.class);
            spriteBatch.draw(texture, part.getX(), part.getY(), 35, 60);
            if (entity.getType() == EntityType.PLAYER) {
                cam.position.x = part.getX();
                cam.position.y = part.getY();
                cam.update();
            }
        }
        //this.renderBackground();
        spriteBatch.end();

        update();
        draw();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }

    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
             sr.setColor(1, 1, 1, 1);
            sr.begin(ShapeRenderer.ShapeType.Line);
            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
                
            }
            texture = new Texture(Gdx.files.internal(entity.getImagePath()));
        spriteBatch = new SpriteBatch();
        
        sprite = new Sprite(texture);
        sprite.setRotation(80);
            sr.end();
        }
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
