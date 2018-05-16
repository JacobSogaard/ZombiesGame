/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.enemywave;

import group8.common.data.GameData;
import group8.common.data.World;
import group8.commonenemy.services.IEnemyPluginService;
import group8.commonenemy.enemy.Rating;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Random;
import org.openide.util.Lookup;

/**
 * Class to handle enemy waves. Sets the properties of a wave. 
 * @author group 8
 */
public class EnemyWave {
    private Lookup.Result<IEnemyPluginService> result;
    private Lookup lookup = Lookup.getDefault();
    private List<IEnemyPluginService> zombies = new CopyOnWriteArrayList<>();
    private double waveCount;
    private GameData gameData;
    private World world;
    
    /**
     * Constructor. Sets the waveCount to 1 since it is first wave when this is called
     */
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
        this.waveCount += 0.06;
        //this.doStuff(gameData, world);
    }
  
  
    //Fills array of zombies for wave
    private void fillZombiesArray() {
        result = Lookup.getDefault().lookupResult(IEnemyPluginService.class);
        this.result.allItems();
        for (IEnemyPluginService ie : result.allInstances()) {
            int max = (int) howManyEnemies(ie);
            for (int i = 0; i < max; i++) {
                ie.start(gameData, world);
            }
            
        }
        
        zombies = new ArrayList();
        //Find the zombie to call the start method on

    }
      
    
    //Arbitrarily calculates how many enemies should be spawned this wave.
    private double howManyEnemies(IEnemyPluginService ie) {
        //Basic variables (remember waveCount is in class scope)
        Random rnd = new Random();
        double baseAmount = 0;
        
        //Get information from specifc enemy and calculate its value
        double rating = ie.getRating().getIntValue(); //NOT IMPLEMENTET CORRECTLY ON ZOMBIE SIDE!
        double value = rating / Rating.getMaxValue();
        value = Math.pow(Rating.getMaxValue(), 2) - (value * Math.pow(Rating.getMaxValue(), 2));
        
        //Calculate probability seed
        double p = baseAmount + value;
        
        double amount = rnd.nextInt((int)p/40) + waveCount;
        return amount;
    }
    
}

