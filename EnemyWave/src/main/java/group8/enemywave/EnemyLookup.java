/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.enemywave;


import group8.commonenemy.services.IEnemyPluginService;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author kasper
 */
public class EnemyLookup {
    private static EnemyLookup instance = null;
    private final Lookup lookup = Lookup.getDefault();
    private List<IEnemyPluginService> zombies = new CopyOnWriteArrayList<>();
    private Lookup.Result<IEnemyPluginService> result;
    private static IEnemyPluginService col;
    
    protected EnemyLookup(){
        result = lookup.lookupResult(IEnemyPluginService.class);
        result.addLookupListener(lookupListener);
        result.allItems();
    }
    
    public static EnemyLookup getInstance(){
        if (instance == null) {
            instance = new EnemyLookup();
        }
        return instance;
    }
    
    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {
            System.out.println("her");

            Collection<? extends IEnemyPluginService> updated = result.allInstances();

            for (IEnemyPluginService us : updated) {
                System.out.println("hejsa");
                // Newly installed modules
                if (!zombies.contains(us)) {
                    //us.start(gameData, world);
                    zombies.add(us);
                    System.out.println("hov");
                }
            }
            // Stop and remove module
            for (IEnemyPluginService gs : zombies) {
                if (!updated.contains(gs)) {
                   // gs.stop(gameData, world);
                    zombies.remove(gs);
                }
            }
        }

    };
}
