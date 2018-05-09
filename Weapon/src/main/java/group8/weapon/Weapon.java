/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.weapon;

import group8.common.data.Entity;

/**
 *
 * @author MER
 */
public class Weapon extends Entity {
    private int[] bulletAttributes;
    private String[] bulletSpritePaths;

    /**
     * @return the bulletAttributes
     */
    public int[] getBulletAttributes() {
        return bulletAttributes;
    }

    /**
     * @return the bulletSpritePaths
     */
    public String[] getBulletSpritePaths() {
        return bulletSpritePaths;
    }

    /**
     * @param bulletAttributes the bulletAttributes to set
     */
    public void setBulletAttributes(int[] bulletAttributes) {
        this.bulletAttributes = bulletAttributes;
    }

    /**
     * @param bulletSpritePaths the bulletSpritePaths to set
     */
    public void setBulletSpritePaths(String[] bulletSpritePaths) {
        this.bulletSpritePaths = bulletSpritePaths;
    }
}
