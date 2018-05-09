/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;

/**
 *
 * @author matiasmarek
 */
public interface IWhoHaveCollidedService  {
    /**
     * The two entity have collided and a damage calculation will be procecced. 
     * @param e1 entity 1 
     * @param e2 entity 2
     */
    public void collisionDetected(Entity e1, Entity e2); 
    
}
    
    

