/*
 * This should be seen as a Collision processor class. 
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IGamePluginService;
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

public class CollisionControlSystem implements IGamePluginService, IStandardCollisionService{
    private int moveAwayFactor = 1; 
    private Lookup lookup = Lookup.getDefault();
    
//    @Override
//    public void process(GameData gameData, World world) {
//        if (frameCount >= 0) {
//           //  checkCollision(world);
//            frameCount =0;
//        }
//        frameCount++;
//    }

    /**
     * This method calculates a rectangle based on a entity and returns it. 
     * @param entity
     * @return entityRectangle  
     */
    private float[] getEntityRect(Entity entity) {
        float[] rectangleArray = new float[4]; 
        rectangleArray[0]= (entity.getShapeX()[0]); //x
        rectangleArray[1] = (entity.getShapeY()[0]);//y
        rectangleArray[2] = (entity.getShapeX()[3] - rectangleArray[0]);//Width
        rectangleArray[3] =  (entity.getShapeY()[1] - rectangleArray[1]);
        return rectangleArray; 
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
    public synchronized boolean detectCollision(Entity entity, World world) {
        //Create list to run collision checks on
        List<Entity> objectsList = new ArrayList();
        objectsList.addAll(world.getEntities());
        //Remove the entity to check on from the list
        objectsList.remove(entity);
        
        //entity1 is an entity calling to see if they have made a collision. 
        Rectangle entity1 = getEntityRect(entity); 
        for(Entity entityOnTheMap : objectsList){
            Rectangle entity2 = getEntityRect(entityOnTheMap);
            Rectangle intersectioRectangle = rectangleIntersection(entity1, entity2); 
            
            //Should the next section be its own method
            if(intersectioRectangle.height > 0 && intersectioRectangle.width > 0){
//                lookup.lookup(IWhoHaveCollidedService.class).collisionDetected(entity, entityOnTheMap); //Tell someone that i have collided.
                
            //Collision detected
            PositionPart placeCorrectly = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            
            //     P -> |T|
            if(movingPart.isRight() // if right button is down. 
                    && placeCorrectly.getX() < entity2.getMinX() // if i move from left to right
                    && entity2.getMinY() <= entity1.getMaxY() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxY() >= entity1.getMinY() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isUp() || movingPart.isDown())){ // and i am only moving with one button. 
            float x = (float) (entity1.getMaxX() - entity2.getMinX());
                    //(intersectioRectangle.width) * moveAwayFactor;
                System.out.println("Player X" + entity1.getMaxX());
                System.out.println("Entity X" + entity1.getMinX());
                System.out.println("Intersection" + intersectioRectangle.getWidth());
            placeCorrectly.setPosition(x, placeCorrectly.getY()); 
            return true;
            }
            
            //  |T| <- q
            else if(movingPart.isLeft()// if right button is down. 
                    && placeCorrectly.getX() > entity2.getMinX() // if i move from right to left.
                    && entity2.getMinY() <= entity1.getMaxY() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxY() >= entity1.getMinY() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isUp() || movingPart.isDown())){ // and i am only moving with one button. 
            float x = placeCorrectly.getX() + (intersectioRectangle.width) * moveAwayFactor;
            placeCorrectly.setPosition(x, placeCorrectly.getY()); 
            return true;
            }

            //  |T| <- q
            else if(movingPart.isDown()// if down button is down. 
                    && entity1.getMinY() <= entity2.getMaxY() // if i move from up to down.  HERE THERE MIGHT BE A PROBLEM
                    && entity2.getMinX()<= entity1.getMaxX() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxX() >= entity1.getMinX() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isRight() || movingPart.isLeft())){ // and i am only moving with one button. 
            float y = placeCorrectly.getY() + (intersectioRectangle.height) * moveAwayFactor;
            placeCorrectly.setPosition(placeCorrectly.getX(),y); 
            return true;
            }

            //  |T| <- q
            else if(movingPart.isUp()// if down button is down. 
                    && entity1.getMaxY() >= entity2.getMinY()// if i move from up to down.  HERE THERE MIGHT BE A PROBLEM
                    && entity2.getMinX() <= entity1.getMaxX() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxX() >= entity1.getMinX() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isRight() || movingPart.isLeft())){ // and i am only moving with one button. 
            float y = placeCorrectly.getY() - (intersectioRectangle.height) * moveAwayFactor;
            placeCorrectly.setPosition(placeCorrectly.getX(),y); 
            return true;
            }
            
                        //     P -> |T|
            else if(movingPart.isRight() && movingPart.isUp() // if right button is down. 
                    && placeCorrectly.getX() < entity2.getMinX() // if i move from left to right
                    && entity2.getMinY() <= entity1.getMaxY() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxY() >= entity1.getMinY() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isDown())){ // and i am only moving with one button. 
            float x = placeCorrectly.getX() - (intersectioRectangle.width) * moveAwayFactor;
            placeCorrectly.setPosition(x, placeCorrectly.getY()); 
            return true;
            }

            //  |T| <- q
            else if(movingPart.isLeft() && movingPart.isUp()// if right button is down. 
                    && placeCorrectly.getX() > entity2.getMinX() // if i move from right to left.
                    && entity2.getMinY() <= entity1.getMaxY() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxY() >= entity1.getMinY() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isDown())){ // and i am only moving with one button. 
            float x = placeCorrectly.getX() + (intersectioRectangle.width) * moveAwayFactor;
            placeCorrectly.setPosition(x, placeCorrectly.getY()); 
            return true;
            }
            
                        //  |T| <- q
            else if(movingPart.isLeft() && movingPart.isDown()// if right button is down. 
                    && placeCorrectly.getX() > entity2.getMinX() // if i move from right to left.
                    && entity2.getMinY() <= entity1.getMaxY() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxY() >= entity1.getMinY() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isUp())){ // and i am only moving with one button. 
            float x = placeCorrectly.getX() + (intersectioRectangle.width) * moveAwayFactor;
            placeCorrectly.setPosition(x, placeCorrectly.getY()); 
            return true;
            }
                        //  |T| <- q
            else if(movingPart.isDown() && movingPart.isLeft()// if down button is down. 
                    && entity1.getMinY() <= entity2.getMaxY() // if i move from up to down.  HERE THERE MIGHT BE A PROBLEM
                    && entity2.getMinX()<= entity1.getMaxX() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxX() >= entity1.getMinX() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isRight())){ // and i am only moving with one button. 
            float y = placeCorrectly.getY()+ (intersectioRectangle.height) * moveAwayFactor;
            placeCorrectly.setPosition(placeCorrectly.getX(),y); 
            return true;
            }
            //  |T| <- q
            else if(movingPart.isDown() && movingPart.isRight()// if down button is down. 
                    && entity1.getMinY() <= entity2.getMaxY() // if i move from up to down.  HERE THERE MIGHT BE A PROBLEM
                    && entity2.getMinX()<= entity1.getMaxX() // if my lowest point is still touching the "Wall" 
                    && entity2.getMaxX() >= entity1.getMinX() // if my lowest point is still touching the "Wall" 
                    && !(movingPart.isLeft())){ // and i am only moving with one button. 
            float y = placeCorrectly.getY()+ (intersectioRectangle.height) * moveAwayFactor;
            placeCorrectly.setPosition(placeCorrectly.getX(),y); 
            return true;
            }
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
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
    }
     




}
