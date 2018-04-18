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
        float acceleration = 300;
        float rotationSpeed = 50;
        float decelaration = 200;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = 3.1415f / 2;
        
        Entity playerRectangle =  new Player();
        playerRectangle.add(new MovingPart(speed, acceleration, rotationSpeed, decelaration));
        playerRectangle.add(new PositionPart(x, y, radians));
        
        playerRectangle.setImagePath(sp.UP);
        
        return playerRectangle; 
    }

    @Override
    public Entity getPlayer() {
        return this.player;
    }
    
}
