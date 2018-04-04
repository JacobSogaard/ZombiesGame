/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.mapobject;

import group8.common.data.Entity;

import java.io.Serializable;

/**
 *
 * @author MER
 */
public class MapObject extends Entity implements Serializable {
    private float xCoor, yCoor, height, width;
   

    /**
     * @return the xCoor
     */
    public float getxCoor() {
        return xCoor;
    }

    /**
     * @return the yCoor
     */
    public float getyCoor() {
        return yCoor;
    }

    /**
     * @param xCoor the xCoor to set
     */
    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    /**
     * @param yCoor the yCoor to set
     */
    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }

    /**
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }
    
    
}
