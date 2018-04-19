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
import java.util.ArrayList;
import java.util.Random;
import org.openide.util.Lookup;

/**
 *
 * @author kasper
 */
public class EnemyWave {
    private Lookup.Result<IEnemyPluginService> result;
    private Lookup lookup = Lookup.getDefault();
    private ArrayList<IEnemyPluginService> zombies;
    private int waveCount;
    
    public EnemyWave() {
        this.waveCount = 1;
    }
    
    /**
     * Finds all relevant zombies and calls the start method from pluginService on them
     */
    public void startNextWave(GameData gameData, World world) {
        this.fillZombiesArray();
        this.doStuff(gameData, world);
    }
    
    private void fillZombiesArray() {
        result = Lookup.getDefault().lookupResult(IEnemyPluginService.class);
        zombies = new ArrayList();
        
        //Find the zombie to call the start method on
        for (IEnemyPluginService ig : result.allInstances()) {
            zombies.add(ig);
        }
    }
    
    private void doStuff(GameData gameData, World world) {
        for (IEnemyPluginService zombie : zombies) {
            zombie.start(gameData, world);
        }
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

