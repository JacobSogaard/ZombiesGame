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
 * @author Bruger
 */
public interface IWeaponService {
    public void setWeaponDirection(Entity entity, World world);
    
    public void changeWeapon();
    
  
}
