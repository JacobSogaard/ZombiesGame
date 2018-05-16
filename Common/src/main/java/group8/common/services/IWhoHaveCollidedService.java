
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 *
 * @author group8
 */
public interface IWhoHaveCollidedService  {
    /**
     * The two entity have collided and a damage calculation will be procecced. 
     * @param e1 entity 1 
     * @param e2 entity 2
     */
    public void collisionDetected(Entity e1, Entity e2, World world); 
    
}
    
    

