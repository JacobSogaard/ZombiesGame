/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;
import org.openide.util.Lookup;

/**
 *
 * @author jacob
 */
public class CollisionRequestServiceImpl {
    private static CollisionRequestServiceImpl instance = null;
    private final Lookup lookup = Lookup.getDefault();
    private Lookup.Result<ICollisionRequestService> result;
    private static ICollisionRequestService col;
    
    protected CollisionRequestServiceImpl(){
        result = lookup.lookupResult(ICollisionRequestService.class);
        //result.addLookupListener(lookupListener);
        result.allItems();
        
        for (ICollisionRequestService service : result.allInstances()) {
            col = service;
        }
    }
    
    public static CollisionRequestServiceImpl getInstance(){
        if (instance == null) {
            instance = new CollisionRequestServiceImpl();
        }
        return instance;
    }
    
    public boolean canMoveUp(Entity entity, World world){
        return col.canMoveUp(entity, world);
    }
    
    public boolean canMoveDown(Entity entity, World world){
        return col.canMoveDown(entity, world);
    }
    
    public boolean canMoveLeft(Entity entity, World world){
        return col.canMoveLeft(entity, world);
    }
    
    public boolean canMoveRight(Entity entity, World world){
        return col.canMoveRight(entity, world);
    }

}
