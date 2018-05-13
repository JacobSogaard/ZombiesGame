/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.player;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
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
 *
 * @author matiasmarek
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
    
    public Entity createPlayer(GameData gameData) {
        float speed = 3;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;
        
        Entity playerRectangle =  new Player();
        playerRectangle.add(new MovingPart(speed));
        playerRectangle.add(new PositionPart(x, y, radians));
        playerRectangle.add(new TimerPart(0));
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

    @Override
    public Entity getPlayer() {
        return this.player;
    }
    
}
