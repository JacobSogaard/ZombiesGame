/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.mapcommon.IMapCollision;
import group8.common.services.IGamePluginService;
import java.util.ArrayList;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {@ServiceProvider(service = IGamePluginService.class)})
public class CollisionPluginSystem implements IGamePluginService  {
    
    
    @Override
    public void start(GameData gameData, World world) {
       
        
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
