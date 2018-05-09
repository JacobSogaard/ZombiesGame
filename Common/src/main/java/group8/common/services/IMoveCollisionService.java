/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 *
 * @author matiasmarek
 */
public interface IMoveCollisionService {
    
    boolean checkRightCollision(Entity entity, World world);
    
    boolean checkLeftCollision(Entity entity, World world);
    
    boolean checkUpCollision(Entity entity, World world);
    
    boolean checkDownCollision(Entity entity, World world);

  
}
