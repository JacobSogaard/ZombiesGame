
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 * @author group8
 */
public interface IWeaponService {
    //Method implemented by WeaponPlugin
    public void setWeaponDirection(Entity entity, World world);
    
    //Method implemented by WeaponPlugin
    public void changeWeapon();
    
  
}
