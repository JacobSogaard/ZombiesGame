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
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.Lookup;
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

    private Weapon weapon;
    private HashMap<Integer, Weapon> weaponMap = new HashMap<>();
    private Integer key = 0;
    private Lookup lookup = Lookup.getDefault();

    @Override
    public void start(GameData gameData, World world) {
        this.setWeapon(weapon, world);
        this.loadJsonWeapons();
        this.weapon = this.weaponMap.get(key);
        world.addEntity(weapon);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(this.weapon);
    }

    private void loadJsonWeapons() {
        this.weaponMap.put(key, weapon);

    }
    
    private Weapon createWeapon(Entity player) {
        PositionPart playerPosition = player.getPart(PositionPart.class);
        MovingPart part = player.getPart(MovingPart.class);
        float x = playerPosition.getX();
        float y = playerPosition.getY();
        float radians = 3.1415f / 2;
        
        this.weapon = this.weaponMap.get(key);
        this.weapon.setHeight(50);
        this.weapon.setWidth(50);
        this.weapon.add(new PositionPart(x, y, radians));
        this.weapon.add(new MovingPart(0));
        if (this.weapon.getImagePath() == null) {
            this.weapon.setImagePath(this.weaponMap.get(key).getBulletSpritePaths()[1]);
        }

        this.setDirection(part);

        return weapon;
    }

//    private void addWeaponPath() {
//        this.weaponMap.put(1, new String[]{"DOWN.png", "UP.png", "LEFT.png", "RIGHT.jpg"});
//        this.weaponMap.put(2, new String[] {"LEFT1.jpg",  "RIGHT1.jpg", "LEFT1.jpg","RIGHT1.jpg"});
//    }

    private void setDirection(MovingPart playerPart) {

        MovingPart weaponPart = this.weapon.getPart(MovingPart.class);

        if (playerPart.isDown()) {
            weaponPart.setDown(true);
            this.weapon.setImagePath(this.weaponMap.get(key).getBulletSpritePaths()[0]);
        }

        if (playerPart.isUp()) {
            weaponPart.setUp(true);
            this.weapon.setImagePath(this.weaponMap.get(key).getBulletSpritePaths()[1]);
        }

        if (playerPart.isLeft()) {
            weaponPart.setLeft(true);
            this.weapon.setImagePath(this.weaponMap.get(key).getBulletSpritePaths()[2]);
            if (playerPart.isUp()) {
                weaponPart.setUp(true);
            } else if (playerPart.isDown()) {
                weaponPart.setDown(true);
            }
        }

        if (playerPart.isRight()) {
            weaponPart.setRight(true);
            this.weapon.setImagePath(this.weaponMap.get(key).getBulletSpritePaths()[3]);
            if (playerPart.isUp()) {
                weaponPart.setUp(true);
            } else if (playerPart.isDown()) {
                weaponPart.setDown(true);
            }
        }
    }

    @Override
    public void setWeapon(Entity entity, World world) {
        this.weapon = this.createWeapon(entity);
        world.addEntity(this.weapon);
    }

    @Override
    public void changeWeapon() {
        if(this.key == 0 || key < this.weaponMap.size()) {
            this.key++;
            this.weapon = this.weaponMap.get(key);
        }
        else if(this.key == this.weaponMap.size()) {
            this.key = 0;
        }
    }

}
