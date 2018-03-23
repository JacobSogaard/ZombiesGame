/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IGamePluginService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MER
 */
public class MapPlugin implements IGamePluginService {
    private ArrayList<Entity> mapObjects = new ArrayList<>();
    
    @Override
    public void start(GameData gameData, World world) {
        System.out.println("1");
        this.createMapObject(world);
        System.out.println("3");
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity m : mapObjects) {
            world.removeEntity(m);
            System.out.println("4");
        }
    }
    
    private void createMapObject(World world) {
        try {
            final InputStream is = new FileInputStream("objects.json");
            for (Iterator it = new ObjectMapper().readValues(new JsonFactory().createParser(is), Map.class); it.hasNext();) {
                System.out.println("2");
                Entity mapObject = (Map) it.next();
                world.addEntity(mapObject);
                mapObjects.add(mapObject);
            } 
        } catch (IOException ex) {
            Logger.getLogger(MapPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
