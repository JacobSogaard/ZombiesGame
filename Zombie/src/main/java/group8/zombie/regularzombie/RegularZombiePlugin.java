/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.regularzombie;

import group8.zombie.bigzombie.*;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.LifePart;
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

public class RegularZombiePlugin implements IEnemyPluginService {
    private Random r = new Random();
    private Enemy zombie;
    
    @Override
    public void start(GameData gameData, World world) {
        //Add entitites to world
        zombie = createZombie(gameData);
        world.addEntity(zombie); 
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(zombie);
        
    }
    
    public Enemy createZombie(GameData gameData) {
        float speed = (float)0.859;
        float x = gameData.getDisplayWidth() / 2 + r.nextInt(500) - 250;
        float y = gameData.getDisplayHeight() / 2 + r.nextInt(500) - 250;
        
        Zombie regularZombie =  new RegularZombie(Rating.THREE);
        regularZombie.add(new MovingPart(speed));
        regularZombie.add(new PositionPart(x, y, 0));
        regularZombie.add(new LifePart(4));

        regularZombie.setImagePath(RegularZombieSpritePath.UP);
        
        return regularZombie; 
    }

    @Override
    public Rating getRating() {
        return Rating.THREE;
    }
}
