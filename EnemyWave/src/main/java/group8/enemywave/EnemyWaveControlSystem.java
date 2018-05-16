package group8.enemywave;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 * Enemywave control system. Handles when the next wave should start
 * @author group 8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
})
public class EnemyWaveControlSystem implements IEntityProcessingService {

    private EnemyWave enemyWave = new EnemyWave();

    @Override
    public void process(GameData gameData, World world) {

        //Check if any more zombies in the world
        boolean noMoreZombies = true;
        for (Entity e : world.getEntities()) {
                if (e.getType() == EntityType.ZOMBIE) {
                    noMoreZombies = false; 
                }
        }

        //Maybe also check if player is standing in the ready-spot or some time has elapsed or something
        if (noMoreZombies) {
            enemyWave.startNextWave(gameData, world);

        }

    }

}
