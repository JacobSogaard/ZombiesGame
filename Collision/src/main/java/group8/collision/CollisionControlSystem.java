/*
 * This should be seen as a Collision processor class. 
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IMoveCollisionService;
import group8.common.services.ISpawnService;
import group8.common.services.IStandardCollisionService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.awt.Rectangle;
import java.util.List;
import org.openide.util.Lookup;
import group8.common.services.IWhoHaveCollidedService;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author group8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IStandardCollisionService.class)
    ,
    @ServiceProvider(service = ISpawnService.class)
})

public class CollisionControlSystem implements IStandardCollisionService, IMoveCollisionService, ISpawnService {

    private Lookup lookup = Lookup.getDefault();

    /**
     * This method calculates a rectangle based on a entity and returns it.
     * @param entity
     * @return entityRectangle
     */
    private Rectangle getEntityRect(Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);

        int x = (int) positionPart.getX(); //x
        int y = (int) positionPart.getY();//y

        int width = (int) entity.getWidth(); //Width
        int height = (int) entity.getHeight(); //Height
        Rectangle rectangle = new Rectangle(x, y, width, height);

        return rectangle;
    }

    /**
     * This method checks for collision between a entity and all other entity on
     * the map
     *
     * @param entity a entity asking to see if they have made a collison
     * @param world holds all entity in the world.
     * @return true if there is a collision / false if same type or no
     * collision.
     */
    @Override
    public boolean detectCollision(Entity entity, World world) {
        List<Entity> objectsList = new ArrayList();
        objectsList.addAll(world.getEntities());
        objectsList.remove(entity);
        MovingPart movingPart = entity.getPart(MovingPart.class);
        Rectangle entityA = getEntityRect(entity);
        entityA.x += movingPart.getSpeed();
        //entity1 is an entity calling to see if they have made a collision. 
        for (Entity entityOnTheMap : objectsList) {
            Rectangle entity2 = getEntityRect(entityOnTheMap);
            //Makes sure a zombie can move through a players weapon.
            if (entity.getType() == EntityType.ZOMBIE && entityOnTheMap.getType() == EntityType.WEAPON) {
                break;
            }
            //Rectangle intersectioRectangle = rectangleIntersection(rectangle, entity2); 
            if (rectangleIntersection(entityA, entity2)) {
                try {
                    lookup.lookup(IWhoHaveCollidedService.class).collisionDetected(entity, entityOnTheMap, world); //Tell someone that i have collided.
                } catch (NullPointerException ex) {

                }
                return true;    
            }

        }
        return false;
    }
    
    //Almost similar to detectCollision, but is used in some the other moving collision methods.
    private boolean boxCollision(Entity entity, Rectangle rectangle, World world) {
        //Create list to run collision checks on
        List<Entity> objectsList = new ArrayList();
        objectsList.addAll(world.getEntities());
        //Remove the entity to check on from the list
        objectsList.remove(entity);

        //entity1 is an entity calling to see if they have made a collision. 
        for (Entity entityOnTheMap : objectsList) {
            Rectangle entity2 = getEntityRect(entityOnTheMap);
           if (entity.getType() == EntityType.ZOMBIE && entityOnTheMap.getType() == EntityType.WEAPON) {
                break;
            }
            //Rectangle intersectioRectangle = rectangleIntersection(rectangle, entity2); 
            if (rectangleIntersection(rectangle, entity2)) {
                try {
                    lookup.lookup(IWhoHaveCollidedService.class).collisionDetected(entity, entityOnTheMap, world); //Tell someone that i have collided.
                } catch (NullPointerException ex) {

                }
                return true;

            }
        }
        return false;
    }

    /**
     * Returns a new rectangle equal to a rectangle between two collided
     * rectangles if they do not intersect, the rectangle returned will be null
     *
     * @param rectangle1
     * @param rectangle2
     * @return rectangle
     */
    public boolean rectangleIntersection(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.intersects(rectangle2);
    }
    
    /**
     * Method for checking if it is possible for a entity to move right. Checks with all other entities.
     * implements IMoveCollisionService
     * 
     * @param entity
     * @param world
     * @return true if it is possible to move right. Returns false if something is blocking that direction.
     */
    @Override
    public boolean checkRightCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);
        float speed = movingPart.getSpeed();
        Rectangle futureRectangle = new Rectangle();

        PositionPart positionPart = entity.getPart(PositionPart.class);

        int x = (int) positionPart.getX(); //x
        int y = (int) positionPart.getY();

        futureRectangle.setRect(x + speed, y, entity.getWidth(), entity.getHeight());
        return boxCollision(entity, futureRectangle, world);
    }

    
    /**
     * Method for checking if it is possible for a entity to move left. 
     * implements IMoveCollisionService
     * @param entity
     * @param world
     * @return true if it is possible to move left. Returns false if something is blocking that direction.
     */
    @Override
    public boolean checkLeftCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);
        float speed = movingPart.getSpeed();
        Rectangle futureRectangle = new Rectangle();

        PositionPart positionPart = entity.getPart(PositionPart.class);

        int x = (int) positionPart.getX(); //x
        int y = (int) positionPart.getY();

        futureRectangle.setRect(x - speed, y, entity.getWidth(), entity.getHeight());
        return boxCollision(entity, futureRectangle, world);
    }

    /**
     * Method for checking if it is possible for a entity to move up.
     * implements IMoveCollisionService
     * 
     * @param entity
     * @param world
     * @return true if it is possible to move up. Returns false if something is blocking that direction.
     */
    @Override
    public boolean checkUpCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);
        float speed = movingPart.getSpeed();
        Rectangle futureRectangle = new Rectangle();
        PositionPart positionPart = entity.getPart(PositionPart.class);

        int x = (int) positionPart.getX(); //x
        int y = (int) positionPart.getY();
        futureRectangle.setRect(x, y + speed, entity.getWidth(), entity.getHeight());

        return boxCollision(entity, futureRectangle, world);

    }

    /**
     * Method for checking if it is possible for a entity to move down. 
     * implements IMoveCollisionService
     * 
     * @param entity
     * @param world
     * @return true if it is possible to move down. Returns false if something is blocking that direction.
     */
    @Override
    public boolean checkDownCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);

        float speed = movingPart.getSpeed();

        Rectangle futureRectangle = new Rectangle();

        PositionPart positionPart = entity.getPart(PositionPart.class);

        int x = (int) positionPart.getX(); //x
        int y = (int) positionPart.getY();
        futureRectangle.setRect(x, y - speed, entity.getWidth(), entity.getHeight());
        return boxCollision(entity, futureRectangle, world);

    }
    
    /**
     * Method for spawning an Entity.
     * This method use PositionPart from an entity to check if another 
     * entity's PositionParts coordinates matches these.
     * 
     * @param entity
     * @param gameData
     * @param world
     * @return 
     */
    @Override
    public Entity spawnHere(Entity entity, GameData gameData, World world) {
        Random rnd = new Random();
        PositionPart pp = entity.getPart(PositionPart.class);
        Rectangle rect = new Rectangle();
        rect.setRect(entity.getShapeX()[1], entity.getShapeY()[1], entity.getWidth(), entity.getHeight());
        while (this.detectCollision(entity, world)) {
            float x = rnd.nextInt(gameData.getDisplayWidth() * 2 - 40);
            float y = rnd.nextInt(gameData.getDisplayHeight() * 2 - 70);
            float[] shapeY = {y, y + entity.getHeight(), y + entity.getHeight(), y};
            float[] shapeX = {x, x, x + entity.getWidth(), x + entity.getWidth()};
            pp.setPosition(x, y);
            entity.setShapeX(shapeX);
            entity.setShapeY(shapeY);
        }

        return entity;
    }

}
