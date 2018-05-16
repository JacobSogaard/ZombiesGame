package group8.zombie.regularzombie;

import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.commonenemy.services.IEnemyPluginService;
import group8.common.services.ISpawnService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.Rating;
import group8.zombie.Zombie;
import java.util.Random;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 * Plugin class for regularZombie. Handles the the start and stop for big zombie 
 * and add it to World
 * @author group 8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEnemyPluginService.class)})

public class RegularZombiePlugin implements IEnemyPluginService {

    private Random r = new Random();
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
     * Method to create a regular zombie. Set it moving- position- life and damage part
     * and the shape of its collisionbox
     * @param gameData gamedata as GameData
     * @param world World
     * @return Returns the zombie that is created as an enemy type.
     */
    public Enemy createZombie(GameData gameData, World world) {
        float speed = (float) 0.859;
        float x = gameData.getDisplayWidth() / 2 + r.nextInt(500) - 250;
        float y = gameData.getDisplayHeight() / 2 + r.nextInt(500) - 250;

        Zombie regularZombie = new RegularZombie(Rating.THREE);
        regularZombie.add(new MovingPart(speed));
        regularZombie.add(new PositionPart(x, y));
        regularZombie.add(new DamagePart(2));
        regularZombie.add(new LifePart(4));

        regularZombie.setImagePath(RegularZombieSpritePath.UP);

        try {
            ISpawnService spawnService = lookup.lookup(ISpawnService.class);
            regularZombie = (Zombie) spawnService.spawnHere(regularZombie, gameData, world);
        } catch (NullPointerException ex) {

        }

        return regularZombie;
    }

    @Override
    public Rating getRating() {
        return Rating.THREE; //Set this zombie rating to 3
    }
}
