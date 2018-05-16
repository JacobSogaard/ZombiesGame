package group8.mapobject;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.mapcommon.IMapCollision;
import group8.common.services.IGamePluginService;
import group8.common.services.ISpawnService;
import java.util.ArrayList;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IMapCollision.class)
})

/**
 * Plugin class for mapobject, handles instantiation of a mapobject entities. Implements the 
 * IGamePluginService and IMapCollision service
 * @author group 8
 */
public class MapObjectPlugin implements IGamePluginService, IMapCollision {

    private static ArrayList<Entity> mapObjects = new ArrayList<>();
    protected static final String MAPOBJECTSPATH = "Images/MapObjects/objects.json";
    private Lookup lookup = Lookup.getDefault();

    @Override
    public void start(GameData gameData, World world) {
        this.createMapObject(gameData, world);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity m : mapObjects) {
            world.removeEntity(m);
        }
        this.clearMap();
    }

    //Clears the map objects arraylist
    private void clearMap() {
        this.mapObjects.clear();
    }
    
    //Creates all wanted map objects and adds them to world
    private void createMapObject(GameData gameData, World world) {
        
        //Change end condition to wanted number of mapobjects.
        for (int i = 0; i <= 10; i++) {
            MapObject map = new MapObject();
            map.add(new PositionPart(map.getXCoor(), map.getYCoor()));
            map.add(new MovingPart(0));
            map = this.initMap(map);
            
            PositionPart pp = map.getPart(PositionPart.class);
            
            //Prompt ISpawnService if the object can spawn at place it is set, 
            //and gets itself back with allowed coordinates.
            try {
                ISpawnService spawn = lookup.lookup(ISpawnService.class);
                map = (MapObject) spawn.spawnHere(map, gameData, world);
            } catch (NullPointerException ex) {
                System.out.println("Create map \n" + ex);
            }
            
            pp = map.getPart(PositionPart.class);
           
            map.setImagePath(map.getMapType().toString());
            this.mapObjects.add(map);
            world.addEntity(map);
        }
    }
    
    //Set shapeX of map object. Returns the new mapobject with new shapeX
    private MapObject setShapeX(MapObject mapObject){
        float[] shapeX = {
            mapObject.getXCoor(),
            mapObject.getXCoor(),
            mapObject.getXCoor() + mapObject.getXSize(),
            mapObject.getXCoor() + mapObject.getXSize()
        };
            mapObject.setShapeX(shapeX);
            return mapObject;
    }
    
    //Set shapeY of map object. Returns the new mapobject with new shapeY
    private MapObject setShapeY(MapObject mapObject){
        float[] shapeY = {
            mapObject.getYCoor(),
            mapObject.getYCoor() + mapObject.getYSize(),
            mapObject.getYCoor() + mapObject.getYSize(),
            mapObject.getYCoor()
        };
            mapObject.setShapeY(shapeY);
            return mapObject;
    }
    
    //Initialize mapobject by calling setShapeX and setShapeY
    private MapObject initMap(MapObject map){
        return this.setShapeY(this.setShapeX(map));
    }

    @Override
    public ArrayList<Entity> getMapObjects() {
        return this.mapObjects;
    }

}
