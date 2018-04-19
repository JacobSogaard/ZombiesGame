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
import group8.common.services.IEnemyPluginService;
import group8.common.services.IGamePluginService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author kasper
 */
public class EnemyWave {
    private Lookup.Result<IEnemyPluginService> result;
    private Lookup lookup = Lookup.getDefault();
    private List<IEnemyPluginService> zombies = new CopyOnWriteArrayList<>();
    private int waveCount;
    private GameData gameData;
    private World world;
    
    public EnemyWave() {
        this.waveCount = 1;
    }
    
    /**
     * Finds all relevant zombies and calls the start method from pluginService on them
     */
    public void startNextWave(GameData gameData, World world) {
        this.gameData = gameData;
        this.world = world;
        this.fillZombiesArray();
        //this.doStuff(gameData, world);
    }
  
    
    private void fillZombiesArray() {
        result = Lookup.getDefault().lookupResult(IEnemyPluginService.class);
        this.result.addLookupListener(lookupListener);
        this.result.allItems();
        
        
        zombies = new ArrayList();
        //Find the zombie to call the start method on
        for (IEnemyPluginService ig : result.allInstances()) {
            zombies.add(ig);
        }
    }
    
//    private void doStuff(GameData gameData, World world) {
//        for (IEnemyPluginService zombie : zombies) {
//            //zombie.start(gameData, world);
//        }
//    }
    
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {

            Collection<? extends IEnemyPluginService> updated = result.allInstances();

            for (IEnemyPluginService us : updated) {
                // Newly installed modules
                if (!zombies.contains(us)) {
                    us.start(gameData, world);
                    zombies.add(us);
                }
            }
            // Stop and remove module
            for (IEnemyPluginService gs : zombies) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData, world);
                    zombies.remove(gs);
                }
            }
        }

    };
    }
    
    //Arbitrarily calculates how many enemies should be spawned this wave.
    private int howManyEnemies() {
        Random rnd = new Random();
        int limit = waveCount;
        boolean oneMoreEnemy = true;
        int amount = 3;
        
        while (oneMoreEnemy && amount < 20) {
            int p = rnd.nextInt(limit) + 1;
            if ((p % 3) < 2) {
                amount++;
            }
            else
                oneMoreEnemy = false;
        }
        
        return amount;
    }
    
}

