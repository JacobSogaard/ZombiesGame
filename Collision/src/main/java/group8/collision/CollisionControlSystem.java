/*
 * This should be seen as a Collision processor class. 
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.commoncollision.IStandardCollisionService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.openide.util.Lookup;

/**
 * @author group8
 */

//Should i implement a CollisionProcessingService.class ? 
//@ServiceProviders(value = {
//    @ServiceProvider(service = IEntityProcessingService.class)
//})

public class CollisionControlSystem {
    private Lookup lookup = Lookup.getDefault(); 
    protected IStandardCollisionService standardCollisionService = lookup.lookup(IStandardCollisionService.class);
   

    /**
     * This method calculates a rectangle based on a entity and returns it. 
     * @param entity
     * @return entityRectangle  
     */
    private Rectangle getEntityRect(Entity entity) {
        //Test to see how much info we loose when converting to int. 
        int x = (int) (entity.getShapeX()[0]); 
        int y = (int) (entity.getShapeY()[0]);
        int width = (int) (entity.getShapeX()[3] - x);
        int height = (int) (entity.getShapeY()[1] - y);
        return new Rectangle(x, y, width, height);  
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
     * This method checks for collision between 2 different entity.
     * @param entity 
     * @param world
     * @return true if there is a collision / false if same type or no collision. 
     */
    private boolean checkCollision(Entity entity, World world) {
        //entity1 is an entity calling to see if they have made a collision. 
        Rectangle entity1 = getEntityRect(entity); 
        for(Entity entityOnTheMap : world.getEntities()){
            //If we check collision on the the same entity
            if(isSameEntity(entity, entityOnTheMap)){
                break;//should return to forloop and try for other elements. 
            }
            Rectangle entity2 = getEntityRect(entityOnTheMap);
            Rectangle intersectioRectangle = rectangleIntersection(entity1, entity2); 
            //Should the next section be its own method
            if(intersectioRectangle != null){
            //Collision detected
            PositionPart placeCorrectly = entity.getPart(PositionPart.class);
            //x should be moved away equal to 1/2 of the intersection rectangle witdth. 
            float x = placeCorrectly.getX() - (intersectioRectangle.width) / 2; 
            //y should be moved away equal to 1/2 og the intersection rectangle height. 
            float y = placeCorrectly.getY() - (intersectioRectangle.height) / 2;
            placeCorrectly.setPosition(x, y); //Move to the correct location. 
            //The collision position have been adjusted, and objects should be placed correct.
            return true; 
            }
        }
        return false;
    }
    
    private void updateCollisionServices(Entity e1, Entity e2) {
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
}
