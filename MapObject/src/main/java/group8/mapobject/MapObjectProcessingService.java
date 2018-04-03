/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.map;



import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {@ServiceProvider(service = IEntityProcessingService.class)})

/**
 *
 * @author MER
 */
public class MapProcessingService implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) { 
        //TODO   
    }

}
