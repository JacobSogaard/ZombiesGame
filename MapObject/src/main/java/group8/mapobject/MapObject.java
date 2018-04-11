/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.mapobject;

import group8.common.data.Entity;
import group8.common.data.EntityType;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author MER
 */
public class MapObject extends Entity implements Serializable {
    private float xCoor, yCoor;
    private int[] xSizes, ySizes, size;
    private SpritePath mapType;
    
    public MapObject() {
        this.setType(EntityType.MAPOBJECT);
        this.xCoor = new Random().nextInt(1400) + 100;
        this.yCoor = new Random().nextInt(1000) + 100;
        this.setType();
        this.setSizes();
        this.setSize();
    }
    
    private void setType() {
        Random rnd = new Random();
        int chosen = rnd.nextInt(SpritePath.values().length);
        this.mapType = SpritePath.values()[chosen];
    }
    
    private void setSizes() {
        switch (this.mapType) {
            case TREE1: 
                this.xSizes = new int[] {50, 55, 60};
                this.ySizes = new int[] {88, 100, 120};
                break;
            case TREE2:
                this.xSizes = new int[] {50, 55, 60};
                this.ySizes = new int[] {88, 100, 120};
                break;
            case ROCK1:
                this.xSizes = new int[] {20, 28};
                this.ySizes = new int[] {25, 33};
                break;
        }
    }
    
    private void setSize() {
        int chosen = new Random().nextInt(this.xSizes.length);
        this.size = new int[] {this.xSizes[chosen], this.ySizes[chosen]};
    }
   
    public float getXSize() {
        return this.size[0];
    }
    
    public float getYSize() {
        return this.size[1];
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
    
    public SpritePath getMapType() {
        return this.mapType;
    }
}