
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.World;

/**
 *
 * @author group8
 */
public interface IShootService {
    //Method implemented by BulletPlugin
    public void shoot(Entity entity, World world);
    
    //Method implemented by BulletPlugin
    public void changeBullet();
}
