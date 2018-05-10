/*
 * This should be seen as a Collision processor class. 
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.services.IGamePluginService;
import group8.common.services.IMoveCollisionService;
import group8.common.services.IStandardCollisionService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.awt.Rectangle;
import java.util.List;
import org.openide.util.Lookup;
import group8.common.services.IWhoHaveCollidedService;
import java.util.ArrayList;

/**
 * @author group8
 */

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IStandardCollisionService.class)
})

public class CollisionControlSystem implements IGamePluginService, IStandardCollisionService, IMoveCollisionService{
    private int moveAwayFactor = 1; 
    private Lookup lookup = Lookup.getDefault();

    /**
     * This method calculates a rectangle based on a entity and returns it. 
     * @param entity
     * @return entityRectangle  
     */
    private Rectangle getEntityRect(Entity entity) {
        int x =(int)  (entity.getShapeX()[1]); //x
        int y =(int)  (entity.getShapeY()[1]);//y
        int width = (int) entity.getWidth(); //Width
        int high= (int)  entity.getHeight();
        Rectangle rectangle = new Rectangle(x, y, width, high); 

        return rectangle; 
    }
    
    /**
     * Checks if two entity are of the same class. 
     * @param entity1 
     * @param entity2
     * @return true if entity is the same / false if entity is not the same
     */
    private static boolean isSameEntity(Entity entity1, Entity entity2) {
        return entity1.getID().equals(entity2.getID());
        //Perhaps redundant. 
    }
    
    /**
     * This method checks for collision between a entity and all other entity on the map
     * @param entity a entity asking to see if they have made a collison
     * @param world holds all entity in the world. 
     * @return true if there is a collision / false if same type or no collision. 
     */
    @Override
    public  boolean detectCollision(Entity entity, World world) {
        List<Entity> objectsList = new ArrayList();
        objectsList.addAll(world.getEntities());
        objectsList.remove(entity);
        MovingPart movingPart = entity.getPart(MovingPart.class); 
        Rectangle entityA = getEntityRect(entity); 
        entityA.x += movingPart.getSpeed(); 
        
        
        //entity1 is an entity calling to see if they have made a collision. 
        
      
        
        for(Entity entityOnTheMap : objectsList){
            Rectangle entity2 = getEntityRect(entityOnTheMap);
            Rectangle intersectioRectangle = rectangleIntersection(entityA, entity2); 
            
            //Should the next section be its own method
            if(intersectioRectangle.height > 0 && intersectioRectangle.width > 0){
                return true; 
//                lookup.lookup(IWhoHaveCollidedService.class).collisionDetected(entity, entityOnTheMap); //Tell someone that i have collided.
            }
        }
        return false;
    }
    
    private boolean boxCollision(Entity entity,Rectangle rectangle, World world){
        //Create list to run collision checks on
        List<Entity> objectsList = new ArrayList();
        objectsList.addAll(world.getEntities());
        //Remove the entity to check on from the list
        objectsList.remove(entity);
        
        //entity1 is an entity calling to see if they have made a collision. 
      
        
        for(Entity entityOnTheMap : objectsList){
            Rectangle entity2 = getEntityRect(entityOnTheMap);
            Rectangle intersectioRectangle = rectangleIntersection(rectangle, entity2); 
            if(intersectioRectangle.height > 0 && intersectioRectangle.width > 0){
                lookup.lookup(IWhoHaveCollidedService.class).collisionDetected(entity, entityOnTheMap, world); //Tell someone that i have collided.
                return true; 
            }
        }
        return false;
    }

    
    /**
     * Returns a new rectangle equal to a rectangle between two collided rectangles
     * if they do not intersect, the rectangle returned will be null
     * @param rectangle1
     * @param rectangle2
     * @return rectangle    
     */
    public Rectangle rectangleIntersection(Rectangle rectangle1, Rectangle rectangle2){
        return rectangle1.intersection(rectangle2);
    }
    
    @Override
    public boolean checkRightCollision(Entity entity, World world){
        MovingPart movingPart = entity.getPart(MovingPart.class);
        float speed = movingPart.getSpeed();
        Rectangle futureRectangle = new Rectangle(); 
        futureRectangle.setRect(entity.getShapeX()[1] + speed, entity.getShapeY()[1], entity.getWidth(), entity.getHeight());
        if(boxCollision(entity,futureRectangle, world)){
            return true; 
        }
        return false;
    }
    
    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
    }


    @Override
    public boolean checkLeftCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);
        float speed = movingPart.getSpeed();
        Rectangle futureRectangle = new Rectangle(); 
        futureRectangle.setRect(entity.getShapeX()[1] - speed, entity.getShapeY()[1], entity.getWidth(), entity.getHeight());
        if(boxCollision(entity,futureRectangle, world)){
            return true; 
        }
        return false;
    }

    @Override
    public boolean checkUpCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);
        float speed = movingPart.getSpeed();   
        Rectangle futureRectangle = new Rectangle(); 
        futureRectangle.setRect(entity.getShapeX()[1], entity.getShapeY()[1] + speed, entity.getWidth(), entity.getHeight());

        if(boxCollision(entity,futureRectangle, world)){
            return true; 
        }
        return false;
        
    }

    @Override
    public boolean checkDownCollision(Entity entity, World world) {
        MovingPart movingPart = entity.getPart(MovingPart.class);

        float speed = movingPart.getSpeed();
        
        
        Rectangle futureRectangle = new Rectangle(); 
        futureRectangle.setRect(entity.getShapeX()[1], entity.getShapeY()[1] - speed, entity.getWidth(), entity.getHeight());
        if(boxCollision(entity,futureRectangle, world)){
            return true; 
        }
        return false;
        
    }




}
