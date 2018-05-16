package group8.bullet;

import group8.common.bulletcreationservice.ILoadBulletService;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IGamePluginService;
import group8.common.services.IShootService;
import java.util.HashMap;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class),
    @ServiceProvider(service = IShootService.class),
    @ServiceProvider(service = ILoadBulletService.class)
})
/**
 * Plugin class for bullet. Handles the instantiation of a bullet. 
 * Implements IGamePluginService, IShootService and ILoadBulletService.
 * @author group 8
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

    //Method to create a bullet. Sets all entity parts for the bullet and 
    //returns the created entity
    private Entity createBullet(Entity shooter) {
        PositionPart positionPart = shooter.getPart(PositionPart.class);
        MovingPart movingPart = shooter.getPart(MovingPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        
        this.bullet = new Bullet();
        this.bullet.setWidth(40);
        this.bullet.setHeight(40);
        this.bullet.add(new PositionPart(x, y));
        this.bullet.add(new MovingPart(10));
        this.bullet.add(new TimerPart(80));
        this.bullet.add(new DamagePart(2));
        this.bullet.add(new LifePart(0));
        
        //If no image set, set it
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
    
    //Method to set the direction the bullet should move in. 
    private void setDirection(MovingPart movingPart, PositionPart positionPart) {

        MovingPart bulletPart = this.bullet.getPart(MovingPart.class);
        PositionPart bPositionPart = this.bullet.getPart(PositionPart.class);
        
        int direction = 0;
        
        //Iterate through all directions from movingpart and gets the direction that is set to true
        for (int i = 0; i < movingPart.getDirection().length; i++) {
            if (movingPart.getDirection()[i] == true) {
                direction = i;
            }
        }
        
         // switch case on the direction int to sets the bullet 
         // image and position in the corect direction
        switch(direction) {
            case 0: bulletPart.setUp(true);
                    bPositionPart.setY(positionPart.getY() + 71 + 80);
                    this.bullet.setImagePath(this.bulletMap.get(key)[1]);
                    break;
                    
            case 1: bulletPart.setDown(true);
                    bPositionPart.setY(positionPart.getY() - 40 - 60);
                    this.bullet.setImagePath(this.bulletMap.get(key)[0]);
                    break;
                    
            case 2: bulletPart.setLeft(true);
                    bPositionPart.setX(positionPart.getX() - 40 - 60);
                    this.bullet.setImagePath(this.bulletMap.get(key)[2]);
                    break;
            
            case 3: bulletPart.setUp(true);
                    bulletPart.setLeft(true);
                    bPositionPart.setY(positionPart.getY() + 71 + 60);
                    bPositionPart.setX(positionPart.getX() - 40 - 55);
                    this.bullet.setImagePath(this.bulletMap.get(key)[2]);
                    break;
                    
            case 4: bulletPart.setDown(true);
                    bulletPart.setLeft(true);
                    bPositionPart.setX(positionPart.getX() - 40 - 40);
                    bPositionPart.setY(positionPart.getY() - 40 - 55);
                    this.bullet.setImagePath(this.bulletMap.get(key)[2]);
                    break;
                    
            case 5: bulletPart.setRight(true);
                    bPositionPart.setX(positionPart.getX() + 40 + 50);
                    this.bullet.setImagePath(this.bulletMap.get(key)[3]);
                    break;
            
            case 6: bulletPart.setUp(true);
                    bulletPart.setRight(true);
                    bPositionPart.setY(positionPart.getY() + 70 + 60);
                    bPositionPart.setX(positionPart.getX() + 40 + 45);
                    this.bullet.setImagePath(this.bulletMap.get(key)[3]);
                    break;
                
            case 7: bulletPart.setDown(true);
                    bulletPart.setRight(true);
                    bPositionPart.setX(positionPart.getX() + 40 + 45);
                    bPositionPart.setY(positionPart.getY() - 40 - 40);
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
