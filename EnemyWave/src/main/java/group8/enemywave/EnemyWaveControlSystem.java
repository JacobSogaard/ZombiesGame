/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.enemywave;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IEntityProcessingService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author kasper
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
