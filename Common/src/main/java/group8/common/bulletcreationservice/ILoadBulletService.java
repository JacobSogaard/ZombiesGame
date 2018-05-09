/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.bulletcreationservice;

import group8.common.data.Entity;
import java.util.Map;

/**
 *
 * @author MER
 */
public interface ILoadBulletService {
    public void setBullet(int speed, int time, int key, String[] spritePaths);
}
