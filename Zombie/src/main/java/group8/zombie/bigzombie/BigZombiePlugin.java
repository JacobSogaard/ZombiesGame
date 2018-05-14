/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.bigzombie;

import group8.common.data.GameData;
import group8.common.data.World;
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
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEnemyPluginService.class)})

public class BigZombiePlugin implements IEnemyPluginService {

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

    public Enemy createZombie(GameData gameData, World world) {

        float speed = (float) 0.5;

        Zombie bigZombie = new BigZombie(Rating.ONE);
        float x = bigZombie.setX(gameData, 200);
        float y = bigZombie.setY(gameData, 200);

        bigZombie.add(new MovingPart(speed));
        bigZombie.add(new PositionPart(x, y, 0));
        bigZombie.add(new LifePart(8));
        bigZombie.setImagePath(BigZombieSpritePath.UP);

        float[] shapex = new float[4];
        float[] shapey = new float[4];

        //here we draw a rectangle for the zombie
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 100);

        shapex[2] = (float) (x + 58);
        shapey[2] = (float) (y + 100);

        shapex[3] = (float) (x + 58);
        shapey[3] = (float) (y);

        bigZombie.setShapeX(shapex);
        bigZombie.setShapeY(shapey);

        try {
            ISpawnService spawnService = lookup.lookup(ISpawnService.class);
            bigZombie = (Zombie) spawnService.spawnHere(bigZombie, gameData, world);
        } catch (NullPointerException ex) {

        }

        return bigZombie;
    }

    @Override
    public Rating getRating() {
        return Rating.ONE;
    }
}
