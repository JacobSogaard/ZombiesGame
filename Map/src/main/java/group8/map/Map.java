/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.map;

import group8.common.data.Entity;
import group8.common.data.entityparts.PositionPart;
import java.io.Serializable;

/**
 *
 * @author MER
 */
public class Map extends Entity implements Serializable {
    private float xCoor;
    private float yCoor;

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
    
    
}
