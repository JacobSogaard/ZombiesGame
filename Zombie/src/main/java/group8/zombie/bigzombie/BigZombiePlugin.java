/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.bigzombie;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEnemyPluginService;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.zombie.Zombie;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEnemyPluginService.class)})

public class BigZombiePlugin implements IEnemyPluginService {
    private Random r = new Random();
    private Entity zombie;
    
    @Override
    public void start(GameData gameData, World world) {
        //Add entitites to world
        System.out.println("Big zombie");
        zombie = createZombie(gameData);
        world.addEntity(zombie); 
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(zombie);
        
    }
    
    public Entity createZombie(GameData gameData) {
        float speed = 5;
        float x = gameData.getDisplayWidth() / 2 + 50;
        float y = gameData.getDisplayHeight() / 2 + 30;
        
        Zombie bigZombie =  new BigZombie();
        bigZombie.add(new MovingPart(speed, 0, 0, 0));
        bigZombie.add(new PositionPart(x, y, 0));
        
        
        bigZombie.setImagePath(SpritePath.UP);
        
        return bigZombie; 
    }
}
