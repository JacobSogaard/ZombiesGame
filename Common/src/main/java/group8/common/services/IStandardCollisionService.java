
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 *
 * @author group8
 */
public interface IStandardCollisionService {

    /**
     * Method implemented by CollisionControlSystem. Used for detecting collision.
     * @param entity
     * @param world
     * @return 
     */
    public boolean detectCollision(Entity entity, World world);
    
    
}
