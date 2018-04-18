/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.awt.Rectangle;

/**
 * @author group8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
})

public class CollisionControlSystem implements IEntityProcessingService {

    

    @Override
    public void process(GameData gameData, World world) {

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
     * Matias: 
     * hvis vi kigger om collision for alle Zombier først, 
     * Hvis vi kigger om collision for alle Player 
     * Altså måske se på alle moving entity om der er collision
     * reager på den collisoin
     * måske gøre således at vi sender et collision objekt der indeholder relevante objekter
     *  - eller vil dette beskade vores kobling. 
     */
    private void checkCollision(Entity entity, World world) {
        //entityRectangle is a rectangle based on the enyity that moves. 
        Rectangle movingEntityRectangle = this.getEntityRect(entity); 
        
        //Rectangle rectangle = this.getEntityRect(entity);
        //Checks for all entity if they have made a collision with 
       // Collection correctedCollection = world.getEntities();
      //  correctedCollection.removeAll(world.getEntities(entity.getClass())); 
        for(Entity somekindOfEntity :  world.getEntities()){
            //If we are not looking at collision between the same object
            if(isSameEntity(somekindOfEntity, entity)){
             Rectangle someKindofEntityRectangle = getEntityRect(somekindOfEntity);
             
            }
        }
        
//        for (Entity e : world.getEntities()) {
//
//            if (!this.isSameEntity(e, entity)) {
//                float[] entity2Rect = this.getEntityRect(e);
//
//                if (entity1Rect[0] < entity2Rect[0] + entity2Rect[2]
//                        && entity1Rect[0] + entity1Rect[2] > entity2Rect[0]
//                        && entity1Rect[1] < entity2Rect[1] + entity2Rect[3]
//                        && entity1Rect[3] + entity1Rect[1] > entity2Rect[1]) {
//                    this.setCollisionDir(entity, e, entity1Rect, entity2Rect, dir);
//                    System.out.println("-----------------------------------------");
//                }
//            }
//        }
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
