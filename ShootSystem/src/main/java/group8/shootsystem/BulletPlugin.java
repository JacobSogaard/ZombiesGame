/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.shootsystem;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.common.services.IShootService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IShootService.class)
})
/**
 *
 * @author MER
 */
public class BulletPlugin implements IGamePluginService, IShootService {
    
    private Entity bullet;

    @Override
    public void start(GameData gameData, World world) {
    }
    
    @Override
    public void shoot(Entity shooter, World world) {
        bullet = this.createBullet(shooter);
        world.addEntity(bullet);
        System.out.println(bullet.getImagePath());
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }
    
    public Entity createBullet(Entity entity) {
        float speed = 4;
        PositionPart p = entity.getPart(PositionPart.class);
        float x = p.getX();
        float y = p.getY();
        float radians = 3.1415f / 2;
        
        bullet = new Bullet();
        bullet.add(new MovingPart(speed));
        bullet.add(new PositionPart(x,y,radians));
        bullet.add(new TimerPart(100));
        bullet.setImagePath("Images/BulletImages/Bullet-bill.png");
        
        return bullet;
    }
    

    
}
