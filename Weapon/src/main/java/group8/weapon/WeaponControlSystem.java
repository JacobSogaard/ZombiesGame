package group8.weapon;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})
/**
 * Weapons control system. Handles movement of the weapon
 * @author group 8
 */
public class WeaponControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        //Iterate through all weapons in World and updates shape to new position
        for (Entity entity : world.getEntities(Weapon.class)) {
            PositionPart position = entity.getPart(PositionPart.class);
            MovingPart moving = entity.getPart(MovingPart.class);

            position.process(gameData, entity);
            moving.process(gameData, entity);

            this.updateShape(entity);

        }
    }

    //Method to update shapeX and shapeY of weapon.
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();

        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + entity.getHeight());

        shapex[2] = (float) (x + entity.getWidth());
        shapey[2] = (float) (y + entity.getHeight());

        shapex[3] = (float) (x + entity.getWidth());
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
