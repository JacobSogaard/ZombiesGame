/*
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 *
 * @author matiasmarek
 */
public interface IStandardCollisionService {

    /**
     * 
     * @param entity
     * @param world
     * @return 
     */
    public boolean detectCollision(Entity entity, World world);
    
    
}
