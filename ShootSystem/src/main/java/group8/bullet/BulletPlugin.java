/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.bullet;

import static group8.bullet.SpritePath.UP;
import group8.common.bulletcreationservice.ILoadBulletService;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.common.services.IShootService;
import java.util.Arrays;
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
    private HashMap<Integer, String[]> bulletMap;
    private Integer key = 1;

    @Override
    public void start(GameData gameData, World world) {
        bulletMap = new HashMap<>();
        this.addBullet();
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

    private Entity createBullet(Entity shooter) {
        PositionPart positionPart = shooter.getPart(PositionPart.class);
        MovingPart movingPart = shooter.getPart(MovingPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = 3.1415f / 2;

        this.bullet = new Bullet();
        this.bullet.setWidth(40);
        this.bullet.setHeight(40);
        this.bullet.add(new PositionPart(x, y, radians));
        this.bullet.add(new MovingPart(10));
        this.bullet.add(new TimerPart(80));
        this.bullet.add(new DamagePart(2));
        this.bullet.add(new LifePart(0));
        if (this.bullet.getImagePath() == null) {
            this.bullet.setImagePath(SpritePath.UP.toString());
        }
        this.setDirection(movingPart, positionPart);
        

        return bullet;
    }

    @Override
    public void setBullet(int speed, int time, int key, String[] spritePaths) {
        this.bullet.add(new MovingPart(speed));
        this.bullet.add(new TimerPart(time));
        this.bulletMap.put(key, spritePaths);
    }
    
    
    private void setDirection(MovingPart movingPart, PositionPart positionPart) {

        MovingPart bulletPart = this.bullet.getPart(MovingPart.class);
        PositionPart bPositionPart = this.bullet.getPart(PositionPart.class);
        
        int direction = 0;
        for (int i = 0; i < movingPart.getDirection().length; i++) {
            if (movingPart.getDirection()[i] == true) {
                direction = i;
            }
        }
        
        switch(direction) {
            case 0: bulletPart.setUp(true);
                    bPositionPart.setY(positionPart.getY() + 71);
                    this.bullet.setImagePath(this.bulletMap.get(key)[1]);
                    break;
                    
            case 1: bulletPart.setDown(true);
                    bPositionPart.setY(positionPart.getY() - 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[0]);
                    break;
                    
            case 2: bulletPart.setLeft(true);
                    bPositionPart.setX(positionPart.getX() - 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[2]);
                    break;
            
            case 3: bulletPart.setUp(true);
                    bulletPart.setLeft(true);
                    bPositionPart.setY(positionPart.getY() + 71);
                    bPositionPart.setX(positionPart.getX() - 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[2]);
                    break;
                    
            case 4: bulletPart.setDown(true);
                    bulletPart.setLeft(true);
                    bPositionPart.setX(positionPart.getX() - 40);
                    bPositionPart.setY(positionPart.getY() - 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[2]);
                    break;
                    
            case 5: bulletPart.setRight(true);
                    bPositionPart.setX(positionPart.getX() + 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[3]);
                    break;
            
            case 6: bulletPart.setUp(true);
                    bulletPart.setRight(true);
                    bPositionPart.setY(positionPart.getY() + 70);
                    bPositionPart.setX(positionPart.getX() + 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[3]);
                    break;
                
            case 7: bulletPart.setDown(true);
                    bulletPart.setRight(true);
                    bPositionPart.setX(positionPart.getX() + 40);
                    bPositionPart.setY(positionPart.getY() - 40);
                    this.bullet.setImagePath(this.bulletMap.get(key)[3]);
                    break;
        }
    }

    private void addBullet() {
        this.bulletMap.put(1, new String[]{"Images/BulletImages/DOWN.png", "Images/BulletImages/UP.png", "Images/BulletImages/LEFT.png", "Images/BulletImages/RIGHT.png"});
        this.bulletMap.put(2, new String[] {"Images/BulletImages/UP1.jpg", "Images/BulletImages/UP1.jpg", "Images/BulletImages/LEFT1.png", "Images/BulletImages/RIGHT1.png"});
    }

    @Override
    public void changeBullet() {
        if (key == 0 || key < this.bulletMap.size()) {
            key++;
        } else if (key == this.bulletMap.size()) {
            key--;
        }
    }

}
