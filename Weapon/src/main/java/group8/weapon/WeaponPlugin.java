/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author MER
 */
public class WeaponPlugin implements IGamePluginService, IWeaponService {

    private Entity weapon;
    private Entity player;
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

//    private void loadJsonWeapons() {
//        ObjectMapper mapper = new ObjectMapper();
//        int i = 0;
//        File jsonFile = new File("Images/weapon/object"+i);
//        while(jsonFile != null) {
//            jsonFile = new File("Images/weapon/object"+i);
//            Weapon w;
//            try {
//                w = mapper.readValue(jsonFile, Weapon.class);
//            } catch (IOException ex) {
//                Logger.getLogger(WeaponPlugin.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
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

        this.setDirection(part);

        return weapon;
    }

    private void addWeaponPath() {
        this.weaponMap.put(1, new String[]{"DOWN.png", "UP.png", "LEFT.png", "RIGHT.jpg"});
        this.weaponMap.put(2, new String[] {"LEFT1.jpg",  "RIGHT1.jpg", "LEFT1.jpg","RIGHT1.jpg"});

    }

    private void setDirection(MovingPart playerPart) {

        MovingPart weaponPart = this.weapon.getPart(MovingPart.class);

        if (playerPart.isDown()) {
            weaponPart.setDown(true);
            this.weapon.setImagePath(this.weaponMap.get(key)[0]);
        }

        if (playerPart.isUp()) {
            weaponPart.setUp(true);
            this.weapon.setImagePath(this.weaponMap.get(key)[1]);
        }

        if (playerPart.isLeft()) {
            weaponPart.setLeft(true);
            this.weapon.setImagePath(this.weaponMap.get(key)[2]);
            if (playerPart.isUp()) {
                weaponPart.setUp(true);
            } else if (playerPart.isDown()) {
                weaponPart.setDown(true);
            }
        }

        if (playerPart.isRight()) {
            weaponPart.setRight(true);
            this.weapon.setImagePath(this.weaponMap.get(key)[3]);
            if (playerPart.isUp()) {
                weaponPart.setUp(true);
            } else if (playerPart.isDown()) {
                weaponPart.setDown(true);
            }
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
