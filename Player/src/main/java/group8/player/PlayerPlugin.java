
package group8.player;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.LifePart;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.playercommon.IPlayerService;
import group8.common.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IPlayerService.class)
})
/**
 * @author group8
 * Plugin class for player, handles instantiation of a player entity. Implements the 
 * IGamePluginService and IPlayerService
 * @author group 8
 */
public class PlayerPlugin implements IGamePluginService, IPlayerService {
    
    private Entity player;
    
    @Override
    public void start(GameData gameData, World world) {
        //Add entitites to world
        player = createPlayer(gameData);
        world.addEntity(player);
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }
    
    /**
     * Method used for creating a player.
     * Uses gamedata to calculate the middle of the map, 
     * so the players coordinates can be set to the middle.
     * @param gameData
     * @return Player entity. 
     */
    public Entity createPlayer(GameData gameData) {
        float speed = 3;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        
        //Creating all the entityparts a player needs.
        Entity playerRectangle =  new Player();
        playerRectangle.add(new MovingPart(speed));
        playerRectangle.add(new PositionPart(x, y));
        playerRectangle.add(new TimerPart(0));
        playerRectangle.add(new LifePart(50));
        playerRectangle.setImagePath(sp.UP);
        
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        //here we draw a rectangle for the zombie
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 70);

        shapex[2] = (float) (x + 40);
        shapey[2] = (float) (y + 70);

        shapex[3] = (float) (x + 40);
        shapey[3] = (float) (y);

        playerRectangle.setShapeX(shapex);
        playerRectangle.setShapeY(shapey);
        
        return playerRectangle; 
    }
    
    /**
     * Method for getting player.
     * @return Player Entity.
     */
    @Override
    public Entity getPlayer() {
        return this.player;
    }
    
}
