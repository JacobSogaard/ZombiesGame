package group8.weapon;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IGamePluginService;
import group8.common.services.IWeaponService;
import java.util.HashMap;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IGamePluginService.class)
    ,
    @ServiceProvider(service = IWeaponService.class)
})

/**
 * Plugin class for weapon. Handles instantiation of weapon entity. Implement the
 * IGamePluginService and IWeaponService
 * @author group 8
 */
public class WeaponPlugin implements IGamePluginService, IWeaponService {

    private Entity weapon;
    private HashMap<Integer, String[]> weaponMap;
    private Integer key = 1;

    @Override
    public void start(GameData gameData, World world) {
        weaponMap = new HashMap<>();
        this.addWeaponPath();

        this.weapon = new Weapon();
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(weapon);
    }

    
    //Method to create a new weapon, returns the newly created weapon and takes the
    //player as parameter.
    private Entity createWeapon(Entity player) {
        PositionPart playerPosition = player.getPart(PositionPart.class);
        MovingPart part = player.getPart(MovingPart.class);
     
        float x = playerPosition.getX();
        float y = playerPosition.getY();
        float radians = 3.1415f / 2;

        this.weapon.setHeight(50);
        this.weapon.setWidth(50);
        this.weapon.add(new PositionPart(x, y, radians));
        this.weapon.add(new MovingPart(0));
        if (this.weapon.getImagePath() == null) {
            this.weapon.setImagePath(this.weaponMap.get(key)[1]);
        }


        this.setDirections(part, playerPosition);


        return weapon;
    }

    private void addWeaponPath() {
        this.weaponMap.put(1, new String[]{"DOWN.png", "UP.png", "LEFT.png", "RIGHT.jpg"});
        this.weaponMap.put(2, new String[] {"LEFT1.jpg",  "RIGHT1.jpg", "LEFT1.jpg","RIGHT1.jpg"});

    }

    
    //Method to set the direction the weapon should be created in.
    private void setDirections(MovingPart movingPart, PositionPart positionPart) {
        
        MovingPart weaponPart = this.weapon.getPart(MovingPart.class);

        PositionPart wPositionPart = this.weapon.getPart(PositionPart.class);
        
        int direction = 0;
        
        //Iterate through all directions from movingpart and gets the direction that is set to true
        for (int i = 0; i < movingPart.getDirection().length; i++) {
            if (movingPart.getDirection()[i] == true) {
                direction = i;
            }
        }
        
        // switch case on the direction int to set weapon image and position in the corect direction
        switch(direction) {
            case 0: weaponPart.setUp(true);
                    wPositionPart.setY(positionPart.getY() + 80);
                    this.weapon.setImagePath(this.weaponMap.get(key)[1]);
                    break;
                    
            case 1: weaponPart.setDown(true);
                    wPositionPart.setY(positionPart.getY() - 60);
                    this.weapon.setImagePath(this.weaponMap.get(key)[0]);
                    break;
                    
            case 2: weaponPart.setLeft(true);
                    wPositionPart.setX(positionPart.getX() - 60);
                    this.weapon.setImagePath(this.weaponMap.get(key)[2]);
                    break;
            
            case 3: weaponPart.setUp(true);
                    weaponPart.setLeft(true);
                    wPositionPart.setY(positionPart.getY() + 60);
                    wPositionPart.setX(positionPart.getX() - 55);
                    this.weapon.setImagePath(this.weaponMap.get(key)[1]);
                    break;
                    
            case 4: weaponPart.setDown(true);
                    weaponPart.setLeft(true);
                    wPositionPart.setY(positionPart.getY() - 40);
                    wPositionPart.setX(positionPart.getX() - 55);
                    this.weapon.setImagePath(this.weaponMap.get(key)[0]);
                    break;
                    
            case 5: weaponPart.setRight(true);
                    wPositionPart.setX(positionPart.getX() + 50);
                    this.weapon.setImagePath(this.weaponMap.get(key)[3]);
                    break;
            
            case 6: weaponPart.setUp(true);
                    weaponPart.setRight(true);
                    wPositionPart.setY(positionPart.getY() + 60);
                    wPositionPart.setX(positionPart.getX() + 45);
                    this.weapon.setImagePath(this.weaponMap.get(key)[1]);
                    break;
                
            case 7: weaponPart.setDown(true);
                    weaponPart.setRight(true);
                    wPositionPart.setY(positionPart.getY() - 40);
                    wPositionPart.setX(positionPart.getX() + 45);
                    this.weapon.setImagePath(this.weaponMap.get(key)[0]);
                    break;
        }
    }

    @Override
    public void setWeaponDirection(Entity entity, World world) {
        weapon = this.createWeapon(entity);
        world.addEntity(weapon);
    }

    @Override
    public void changeWeapon() {
        if(key == 0 || key < this.weaponMap.size()) {
            key++;
        }
        else if(key == this.weaponMap.size()) {
            key--;
        }
    }

}
