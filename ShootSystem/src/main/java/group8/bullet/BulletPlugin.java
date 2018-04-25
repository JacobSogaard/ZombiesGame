/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.bullet;

import group8.common.bulletcreationservice.ILoadBulletService;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.common.services.IShootService;
import java.util.HashMap;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IShootService.class),
    @ServiceProvider(service = ILoadBulletService.class)
})
/**
 *
 * @author MER
 */
public class BulletPlugin implements IGamePluginService, IShootService, ILoadBulletService {

    private Entity bullet;
    private HashMap<String,String> sprites;

    @Override
    public void start(GameData gameData, World world) {
        this.loadSprites();
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
        PositionPart p = entity.getPart(PositionPart.class);
        MovingPart part = entity.getPart(MovingPart.class);
        float x = p.getX();
        float y = p.getY();
        float radians = 3.1415f / 2;

        this.bullet = new Bullet();
        this.bullet.add(new PositionPart(x, y, radians));
        if (this.bullet.getImagePath() == null) {
            this.setBullet(7, 60, this.sprites);
        }
        this.setDirection(part);

        return bullet;
    }
    
    public void setBullet(int speed, int time, Map spritePaths) {
        this.bullet.add(new MovingPart(speed));
        this.bullet.add(new TimerPart(time));
        this.sprites = (HashMap<String, String>) spritePaths;
    }

    private void setDirection(MovingPart part) {

        MovingPart bulletPart = this.bullet.getPart(MovingPart.class);

        if (part.isDown()) {
            bulletPart.setDown(true);
            this.bullet.setImagePath(this.sprites.get("DOWN"));
        }

        if (part.isUp()) {
            bulletPart.setUp(true);
            this.bullet.setImagePath(this.sprites.get("UP"));
        }

        if (part.isLeft()) {
            bulletPart.setLeft(true);
            this.bullet.setImagePath(this.sprites.get("LEFT"));
            if (part.isUp()) {
                bulletPart.setUp(true);
            } else if (part.isDown()) {
                bulletPart.setDown(true);
            }
        }

        if (part.isRight()) {
            bulletPart.setRight(true);
            this.bullet.setImagePath(this.sprites.get("RIGHT"));
            if (part.isUp()) {
                bulletPart.setUp(true);
            } else if (part.isDown()) {
                bulletPart.setDown(true);
            }
        }
    }
    
    private void loadSprites() {
        this.sprites.put("UP", SpritePath.UP.toString());
        this.sprites.put("DOWN", SpritePath.DOWN.toString());
        this.sprites.put("LEFT", SpritePath.LEFT.toString());
        this.sprites.put("RIGHT", SpritePath.RIGHT.toString());
    }

}
