/*
 * 
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 *
 * @author matiasmarek
 */
public interface IMoveCollisionService {
    
      public boolean checkRightCollision(Entity entity, World world);
      public boolean  checkLeftCollision(Entity entity, World world);
      public boolean  checkUpCollision(Entity entity, World world);
      public boolean  checkDownCollision(Entity entity, World world);
      
      
    
}
