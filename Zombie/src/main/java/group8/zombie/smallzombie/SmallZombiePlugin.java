/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.smallzombie;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.commonenemy.services.IEnemyPluginService;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.Rating;
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

public class SmallZombiePlugin implements IEnemyPluginService {
    private Random r = new Random();
    private Enemy zombie;
    
    @Override
    public void start(GameData gameData, World world) {
        //Add entitites to world
        System.out.println("Small Zombie");
        zombie = createZombie(gameData);
        world.addEntity(zombie); 
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(zombie);
        
    }
    
    public Enemy createZombie(GameData gameData) {
        float speed = 1;
        float x = gameData.getDisplayWidth() / 2 + r.nextInt(200) - 80;
        float y = gameData.getDisplayHeight() / 2 + r.nextInt(200) - 80;
        
        Zombie smallZombie =  new SmallZombie(Rating.THREE);
        smallZombie.add(new MovingPart(speed, 0, 0, 0));
        smallZombie.add(new PositionPart(x, y, 0));
        
        
        smallZombie.setImagePath(SmallZombieSpritePath.UP);
        
        return smallZombie; 
    }

    @Override
    public Rating getRating() {
        return this.zombie.getRating();
    }
}
