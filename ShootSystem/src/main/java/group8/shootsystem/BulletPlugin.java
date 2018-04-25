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
    @ServiceProvider(service = IGamePluginService.class)
    ,
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
        TimerPart p = shooter.getPart(TimerPart.class);
        if (p.getExpiration() == 0) {
            bullet = this.createBullet(shooter);
            p.setExpiration(10);
            world.addEntity(bullet);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(bullet);
    }

    private Entity createBullet(Entity entity) {
        float speed = 10;
        PositionPart p = entity.getPart(PositionPart.class);
        MovingPart part = entity.getPart(MovingPart.class);
        float x = p.getX();
        float y = p.getY();
        float radians = 3.1415f / 2;

        bullet = new Bullet();
        bullet.add(new MovingPart(speed));
        bullet.add(new PositionPart(x, y, radians));
        bullet.add(new TimerPart(60));
        bullet.setImagePath(SpritePath.UP.toString());
        setDirection(part);

        return bullet;
    }

    public void setDirection(MovingPart part) {

        MovingPart bulletPart = bullet.getPart(MovingPart.class);

        if (part.isDown()) {
            bulletPart.setDown(true);
            bullet.setImagePath(SpritePath.DOWN.toString());
        }

        if (part.isUp()) {
            bulletPart.setUp(true);
            bullet.setImagePath(SpritePath.UP.toString());
        }

        if (part.isLeft()) {
            bulletPart.setLeft(true);
            bullet.setImagePath(SpritePath.LEFT.toString());
            if (part.isUp()) {
                bulletPart.setUp(true);
            } else if (part.isDown()) {
                bulletPart.setDown(true);
            }
        }

        if (part.isRight()) {
            bulletPart.setRight(true);
            bullet.setImagePath(SpritePath.RIGHT.toString());
            if (part.isUp()) {
                bulletPart.setUp(true);
            } else if (part.isDown()) {
                bulletPart.setDown(true);
            }
        }
    }

}
