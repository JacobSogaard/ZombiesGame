/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.playercommon;

import group8.common.data.Entity;
import group8.common.services.CollisionRequestServiceImpl;
import group8.common.services.ICollisionRequestService;
import org.openide.util.Lookup;

/**
 *
 * @author kasper
 */
public class PlayerServiceImpl {
    private static PlayerServiceImpl instance = null;
    private final Lookup lookup = Lookup.getDefault();
    private Lookup.Result<IPlayerService> result;
    private static IPlayerService player;
    
    protected PlayerServiceImpl(){
        result = lookup.lookupResult(IPlayerService.class);
        //result.addLookupListener(lookupListener);
        result.allItems();
        
        for (IPlayerService service : result.allInstances()) {
            player = service;
        }
    }
    
    public static PlayerServiceImpl getInstance(){
        if (instance == null) {
            instance = new PlayerServiceImpl();
        }
        return instance;
    }
    
    public Entity getPlayer() {
        return player.getPlayer();
    }
}
