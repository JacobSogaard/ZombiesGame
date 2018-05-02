/*
 * This should be seen as a Collision processor class. 
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.List;
import org.openide.util.Lookup;

/**
 * @author group8
 */

//Should i implement a CollisionProcessingService.class ? 
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class CollisionControlSystem implements IEntityProcessingService{
    private Lookup lookup = Lookup.getDefault();
    private int frameCount = 0;
    
    @Override
    public void process(GameData gameData, World world) {
        if (frameCount >= 0) {
            checkCollision(world);
            frameCount =0;
        }
        frameCount++;
    }

    /**
     * This method calculates a rectangle based on a entity and returns it. 
     * @param entity
     * @return entityRectangle  
     */
    private Rectangle getEntityRect(Entity entity) {
        System.out.println("Collision : getEntityRect");
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
        System.out.println("CollisionControlSystem : isSameEntity()");
        return entity1.getID().equals(entity2.getID());
        //Perhaps redundant. 
    }
    
    /**
     * This method checks for collision between 2 different entity.
     * @param entity 
     * @param world
     * @return true if there is a collision / false if same type or no collision. 
     */
//    private boolean checkCollision(Entity entity, World world) {
//        //entity1 is an entity calling to see if they have made a collision. 
//        Rectangle entity1 = getEntityRect(entity); 
//        for(Entity entityOnTheMap : world.getEntities()){
//            //If we check collision on the the same entity
//            if(isSameEntity(entity, entityOnTheMap)){
//                break;//should return to forloop and try for other elements. 
//            }
//            Rectangle entity2 = getEntityRect(entityOnTheMap);
//            Rectangle intersectioRectangle = rectangleIntersection(entity1, entity2); 
//            //Should the next section be its own method
//            if(intersectioRectangle != null){
//            //Collision detected
//            PositionPart placeCorrectly = entity.getPart(PositionPart.class);
//            //x should be moved away equal to 1/2 of the intersection rectangle witdth. 
//            float x = placeCorrectly.getX() - (intersectioRectangle.width) / 2; 
//            //y should be moved away equal to 1/2 og the intersection rectangle height. 
//            float y = placeCorrectly.getY() - (intersectioRectangle.height) / 2;
//            placeCorrectly.setPosition(x, y); //Move to the correct location. 
//            //The collision position have been adjusted, and objects should be placed correct.
//            return true; 
//            }
//        }
//        return false;
//    }
//    
    /**
     * This method checks for collision between 2 different entity.
     * @param entity 
     * @param world
     * @return true if there is a collision / false if same type or no collision. 
     */
    private void checkCollision(World world) {
        
        //make a list containing all Entity in the world. 
        ArrayList<Entity> listOfEntity = new ArrayList<>(); 
        listOfEntity.addAll(world.getEntities());
        //an Entity checking all entity's to see if there have been a collision. 
        while (!listOfEntity.isEmpty()) {
            //System.out.println("checkCollision() WHILE LOOP");
            
            Entity entity = listOfEntity.get(0); //Get the first index in the list.
        //Are entity 1 colliding with an entity.
        Rectangle entity1 = getEntityRect(entity);
        for(Entity entityOnTheMap : world.getEntities()){
            //System.out.println("checkCollision() for()");
        //If we check collision on the the same entity
            if(isSameEntity(entity, entityOnTheMap)){
              //  System.out.println("checkCollision() if LOOP");
                break;//should return to forloop and try for other elements. 
            }
            Rectangle entity2 = getEntityRect(entityOnTheMap);
            Rectangle intersectioRectangle = rectangleIntersection(entity1, entity2);
            //Should the next section be its own method
            if(intersectioRectangle.height > 0 && intersectioRectangle.width > 0){
                System.out.println("Height : " + intersectioRectangle.height + "Width : " +  intersectioRectangle.width);
                System.out.println("COLLISION DETECTED");
            //Collision detected
            PositionPart placeCorrectly = entity.getPart(PositionPart.class);
            PositionPart placeCorrectlyEntityOnTheMap =  entityOnTheMap.getPart(PositionPart.class); 
            //x should be moved away equal to 1/2 of the intersection rectangle witdth. 
            float x = placeCorrectly.getX() - (intersectioRectangle.width) / 2; 
            float xEntityOnMap = placeCorrectlyEntityOnTheMap.getX() + (intersectioRectangle.width) /2;
                
            //y should be moved away equal to 1/2 og the intersection rectangle height. 
            float y = placeCorrectly.getY() - (intersectioRectangle.height) / 2;
            float yEntityOnTheMap = placeCorrectlyEntityOnTheMap.getY() + (intersectioRectangle.height) / 2;
            placeCorrectly.setPosition(x, y); //Move to the correct location. 
            System.out.println("Entity coordinate : " + "X =" + x + "Y = " + y );
                System.out.println("EntityOnTheMap : " + "X = " + xEntityOnMap + "Y = " + yEntityOnTheMap);
            placeCorrectlyEntityOnTheMap.setPosition(xEntityOnMap, yEntityOnTheMap);
            
            //The collision position have been adjusted, and objects should be placed correct.           
            }
        }
        //The following entity have not made a collision
        listOfEntity.remove(0); 
        }
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
