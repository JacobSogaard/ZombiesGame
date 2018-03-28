/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.PositionPart;
import group8.common.mapcommon.IMapCollision;
import group8.common.services.IGamePluginService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value
        = {
            @ServiceProvider(service = IGamePluginService.class)
            ,@ServiceProvider(service = IMapCollision.class)})

/**
 *
 * @author MER
 */
public class MapPlugin implements IGamePluginService, IMapCollision {

    private static ArrayList<Entity> mapObjects = new ArrayList<>();
    protected static final String MAPOBJECTSPATH = "Images/MapObjects/objects.json";

    @Override
    public void start(GameData gameData, World world) {
        this.createMapObject(world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        System.out.println("STOP");
        for (Entity m : mapObjects) {
            world.removeEntity(m);
        }
        this.clearMap();
    }

    private void clearMap() {
        this.mapObjects.clear();
    }

    private void createMapObject(World world) {

        try {
            final InputStream is = new FileInputStream(MAPOBJECTSPATH);
            ObjectMapper objectMapper = new ObjectMapper();
            this.mapObjects = objectMapper.readValue(is, new TypeReference<List<Map>>() {
            });
            for (Entity m : mapObjects) {
                Map map = (Map) m;
                map.add(new PositionPart(map.getxCoor(), map.getyCoor(), 0));
                world.addEntity(map);
            }
        } catch (IOException ex) {
            Logger.getLogger(MapPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Entity> getMapObjects() {
        return this.mapObjects;
    }

}
