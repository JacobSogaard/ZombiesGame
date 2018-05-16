/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.smallzombie;

import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.ISpawnService;
import group8.commonenemy.services.IEnemyPluginService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.Rating;
import group8.zombie.Zombie;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 * Plugin class for smallZombie. Handles the the start and stop for big zombie 
 * and add it to World
 * @author group 8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEnemyPluginService.class)})

public class SmallZombiePlugin implements IEnemyPluginService {

    private Enemy zombie;
    private Lookup lookup = Lookup.getDefault();

    @Override
    public void start(GameData gameData, World world) {
        //Add entitites to world
        zombie = createZombie(gameData, world);
        world.addEntity(zombie);

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(zombie);

    }

    /**
     * Method to create a small zombie. Set it moving- position- life and damage part
     * and the shape of its collisionbox
     * @param gameData gamedata as GameData
     * @param world World
     * @return Returns the zombie that is created as enemy object
     */
    public Enemy createZombie(GameData gameData, World world) {
        float speed = (float) 1.5;
        Zombie smallZombie = new SmallZombie(Rating.THREE);
        float x = smallZombie.setX(gameData, 200);
        float y = smallZombie.setY(gameData, 200);

        smallZombie.add(new MovingPart(speed));
        smallZombie.add(new PositionPart(x, y, 0));
        smallZombie.add(new LifePart(2));
        smallZombie.add(new DamagePart(2));
        smallZombie = (Zombie) lookup.lookup(ISpawnService.class).spawnHere(smallZombie, gameData, world);

        smallZombie.setImagePath(SmallZombieSpritePath.UP);
        
        try {
            ISpawnService spawnService = lookup.lookup(ISpawnService.class);
            smallZombie = (Zombie) spawnService.spawnHere(smallZombie, gameData, world);
        } catch (NullPointerException ex) {

        }

        return smallZombie;
    }

    @Override
    public Rating getRating() {
        return Rating.SEVEN; //Set this zombie rating to 7
    }
}
