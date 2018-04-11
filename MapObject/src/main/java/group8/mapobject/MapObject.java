/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.mapobject;

import group8.common.data.Entity;
import group8.common.data.EntityType;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author MER
 */
public class MapObject extends Entity implements Serializable {
    private float xCoor, yCoor;
    private int[] xSizes = {20, 40, 60}, ySizes = {80, 100, 110};
    
    public MapObject() {
        this.setType(EntityType.MAPOBJECT);
        this.xCoor = new Random().nextInt(1500) + 50;
        this.yCoor = new Random().nextInt(1100) + 50;
    }
   
    public float getXSize() {
        return this.xSizes[new Random().nextInt(3)];
    }
    
    public float getYSize() {
        return this.ySizes[new Random().nextInt(3)];
    }
    
    /**
     * @return the xCoor
     */
    public float getXCoor() {
        return xCoor;
    }

    /**
     * @return the yCoor
     */
    public float getYCoor() {
        return yCoor;
    }

    /**
     * @param xCoor the xCoor to set
     */
    public void setXCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    /**
     * @param yCoor the yCoor to set
     */
    public void setYCoor(int yCoor) {
        this.yCoor = yCoor;
    }
    
    public void setXYCoor() {
        this.xCoor = new Random().nextInt(1500) + 50;
        this.yCoor = new Random().nextInt(1100) + 50;
    }

    /**
     * @return the xSizes
     */
    public int[] getXSizes() {
        return xSizes;
    }

    /**
     * @return the ySizes
     */
    public int[] getYSizes() {
        return ySizes;
    }

    /**
     * @param xSizes the xSizes to set
     */
    public void setXSizes(int[] xSizes) {
        this.xSizes = xSizes;
    }

    /**
     * @param ySizes the ySizes to set
     */
    public void setYSizes(int[] ySizes) {
        this.ySizes = ySizes;
    }
}