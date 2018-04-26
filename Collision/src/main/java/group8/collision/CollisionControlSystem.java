/*
 * This should be seen as a Collision processor class. 
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IEntityProcessingService;
import group8.commoncollision.IStandardCollisionService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Lookup;

/**
 * @author group8
 */

//Should i implement a CollisionProcessingService.class ? 
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class CollisionControlSystem implements IEntityProcessingService {
    private Lookup lookup = Lookup.getDefault(); 
    protected IStandardCollisionService standardCollisionService = lookup.lookup(IStandardCollisionService.class);
   

    @Override
    public void process(GameData gameData, World world) {
            checkCollision(world);
    }

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
     * Note : Det er denne service som dette komponent kan tilbyde.
     * This method checks for collision between 2 different entity.
     * @param entity 
     * @param world
     * @return true if there is a collision / false if same type or no collision. 
     */
    private boolean checkCollision(World world) {
        ArrayList<Entity> entityList = new ArrayList<>(); 
        entityList.addAll(world.getEntities());//(ArrayList<Entity>) world.getEntities(); //Create a temporary list of entity.
        System.out.println("Check Collision");
        //entityRectangle is a rectangle based on the enyity.
       
        //Look for collesion between all entity in the world.
        while (!entityList.isEmpty()) {
            Rectangle movingEntityRectangle = this.getEntityRect(entityList.get(0)); //Draw a rectangle from the first element in the arraylist.
            Entity entity = entityList.get(0);
            for(Entity somekindOfEntity : entityList){
            //make sure we do not check on the identical entity.
            if(!isSameEntity(somekindOfEntity, entity)){
                
                //Draw the rectangle  for any given entity that is not the "original" entity. 
             Rectangle someKindofEntityRectangle = getEntityRect(somekindOfEntity);
                //Get the collisionRectangle. 
                Rectangle collisionRectangle = rectangleIntersection(movingEntityRectangle, someKindofEntityRectangle);
                //collisionRectanlge is null when there is no coliison
                if(collisionRectangle == null){
                    return false; //Collision not happening
                }
                //there is a collision rectangle and therefor collision. 
                else{
                    this.updateCollisionServices(somekindOfEntity, entity);
                    return true; //Collision between 2 entitys.
                }
            }
        }
            entityList.remove(0);
        }
        
    return false; 
    }
    
    private void updateCollisionServices(Entity e1, Entity e2) {
    }
        
    
    
    /**
     * Returns a new rectangle equal to a rectangle between two collided rectangles
     * @param rectangle1
     * @param rectangle2
     * @return rectangle     
     */
    public Rectangle rectangleIntersection(Rectangle rectangle1, Rectangle rectangle2){
        return rectangle1.intersection(rectangle2);
    }
}
