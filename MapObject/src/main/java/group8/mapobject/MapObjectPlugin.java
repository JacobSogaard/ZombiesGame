/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.mapobject;

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
public class MapObjectPlugin implements IGamePluginService, IMapCollision {

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
            this.mapObjects = objectMapper.readValue(is, new TypeReference<List<MapObject>>() {
            });
            for (Entity m : mapObjects) {
<<<<<<< HEAD
                for (int i = 0; i < 10; i++) {
                MapObject map = new MapObject();
                map.add(new PositionPart(map.getXCoor(), map.getYCoor(), 0));
=======
                MapObject map = (MapObject) m;
                map.add(new PositionPart(map.getxCoor(), map.getyCoor(), 0));
                
>>>>>>> 809384f3784ea950ad1b9b5d401e5d362c1c9d3f
                world.addEntity(this.initMap(map));
                System.out.println();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MapObjectPlugin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setShapeX(MapObject mapObject){
        float[] shapeX = {
            mapObject.getXCoor(),
            mapObject.getXCoor(),
            mapObject.getXCoor() + mapObject.getXSize(),
            mapObject.getXCoor() + mapObject.getXSize()};
            mapObject.setShapeX(shapeX);
    }
    
    private void setShapeY(MapObject mapObject){
        float[] shapeY = {
            mapObject.getYCoor(),
            mapObject.getYCoor() + mapObject.getYSize(),
            mapObject.getYCoor() + mapObject.getYSize(),
            mapObject.getYCoor()
        };
            mapObject.setShapeY(shapeY);
    }
    
    private MapObject initMap(MapObject map){
        this.setShapeX(map);
        this.setShapeY(map);
        return map;
    }

    @Override
    public ArrayList<Entity> getMapObjects() {
        return this.mapObjects;
    }

}
