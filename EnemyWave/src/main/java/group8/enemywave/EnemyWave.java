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
import group8.commonenemy.services.IEnemyPluginService;
import group8.common.services.IGamePluginService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.Rating;
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
    private double waveCount;
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
        this.waveCount += 0.2;
        //this.doStuff(gameData, world);
    }
  
    
    private void fillZombiesArray() {
        result = Lookup.getDefault().lookupResult(IEnemyPluginService.class);
        this.result.allItems();
        
        for (IEnemyPluginService ie : result.allInstances()) {
            for (int i = 0; i < howManyEnemies(ie); i++) {
                ie.start(gameData, world);
            }
        }
        
        zombies = new ArrayList();
        //Find the zombie to call the start method on

    }
    
    private void doStuff(GameData gameData, World world) {
        for (IEnemyPluginService zombie : zombies) {
            zombie.start(gameData, world);
        }
    }
    
    
    
    //Arbitrarily calculates how many enemies should be spawned this wave.
    private double howManyEnemies(IEnemyPluginService ie) {
        //Basic variables (remember waveCount is in class scope)
        Random rnd = new Random();
        double baseAmount = 4;
        
        //Get information from specifc enemy and calculate its value
        //double rating = ie.getRating().getIntValue();
        double rating = 3;
        double value = rating / Rating.getMaxValue();
        
        //Calculate probability seed
        double p = baseAmount + value;
        
        double amount = rnd.nextInt((int)p) + waveCount;
        return amount;
    }
    
}

