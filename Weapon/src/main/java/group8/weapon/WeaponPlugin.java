/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.weapon;

import com.fasterxml.jackson.databind.ObjectMapper;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IGamePluginService;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)
})

/**
 *
 * @author MER
 */
public class WeaponPlugin implements IGamePluginService {
    
    private Weapon w;
    
    @Override
    public void start(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void loadJsonWeapons() {
        ObjectMapper mapper = new ObjectMapper();
        int i = 0;
        File jsonFile = new File("Images/weapon/object"+i);
        while(jsonFile != null) {
            jsonFile = new File("Images/weapon/object"+i);
            Weapon w;
            try {
                w = mapper.readValue(jsonFile, Weapon.class);
            } catch (IOException ex) {
                Logger.getLogger(WeaponPlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
