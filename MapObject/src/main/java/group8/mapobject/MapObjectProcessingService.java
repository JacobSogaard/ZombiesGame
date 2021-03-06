package group8.mapobject;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})

/**
 * MapObject control system. Handles the movement of the mapobject. Extends the
 * IEntityProcessingService. Should not be called as of now, but makes it possible
 * to have movable map objects
 * @author group 8
 */
public class MapObjectProcessingService implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getType() == EntityType.MAPOBJECT) {
                this.updateShape((MapObject) e);
            }
        }
    }

    private void updateShape(MapObject mapObj) {
        float[] shapex = mapObj.getShapeX();
        float[] shapey = mapObj.getShapeY();
        PositionPart positionPart = mapObj.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        

        //here we draw a rectangle for the player
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + mapObj.getYSize());

        shapex[2] = (float) (x + mapObj.getXSize());
        shapey[2] = (float) (y + mapObj.getYSize());

        shapex[3] = (float) (x + mapObj.getXSize());
        shapey[3] = (float) (y);

        mapObj.setShapeX(shapex);
        mapObj.setShapeY(shapey);
    }

}
