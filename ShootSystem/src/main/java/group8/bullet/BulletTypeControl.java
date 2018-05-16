package group8.bullet;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.LifePart;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IStandardCollisionService;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})

/**
 * Control system for bullets, implements the IEntityProcessingService
 * @author group 8
 */
public class BulletTypeControl implements IEntityProcessingService {
    private Lookup lookup = Lookup.getDefault();
    
    @Override
    public void process(GameData gameData, World world) {
        
         //Iterate through all bullets in World and updates shape to new position
        for (Entity entity : world.getEntities(Bullet.class)) {
            PositionPart part1 = entity.getPart(PositionPart.class);
            MovingPart moving = entity.getPart(MovingPart.class);
            TimerPart timer = entity.getPart(TimerPart.class);
            LifePart life = entity.getPart(LifePart.class);
            
            part1.process(gameData, entity);
            moving.process(gameData, entity);
            timer.process(gameData, entity);
            life.process(gameData, entity);

            bulletTimer(timer, world, entity);
            
            //Prompts collision to check collision.
            try {
                IStandardCollisionService colService = lookup.lookup(IStandardCollisionService.class);
                colService.detectCollision(entity, world);
            } catch (NullPointerException ex) {
                
            }
            
            
            this.updateShape(entity);
        }
    }

    //Methoid to check if the bullet has reached it's expiration
    private void bulletTimer(TimerPart timer, World world, Entity entity) {
        if (timer.getExpiration() == 0) {
            world.removeEntity(entity);
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
