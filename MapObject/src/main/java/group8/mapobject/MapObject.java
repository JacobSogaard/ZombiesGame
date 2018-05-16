package group8.mapobject;

import group8.common.data.Entity;
import group8.common.data.EntityType;

import java.io.Serializable;
import java.util.Random;

/**
 * Constructor class for mapobject. Map objects are all the objects entities should
 * not be able to move through on map.
 * @author group 8
 */
public class MapObject extends Entity implements Serializable {
    private float xCoor, yCoor;
    private int[] xSizes, ySizes, size;
    private SpritePath mapType;
    
    /**
     * Constructor for mapobject. Sets a random x and y coordinate and entitytype 
     * to mapobject
     */
    public MapObject() {
        this.setType(EntityType.MAPOBJECT);
        this.xCoor = new Random().nextInt(1400) + 100;
        this.yCoor = new Random().nextInt(1000) + 100;
        this.setType();
        this.setSizes();
        this.setSize();
    }
    
    //Randomly chooses one of the three mapobject types (tree1, tree2 and rock)
    private void setType() {
        Random rnd = new Random();
        int chosen = rnd.nextInt(SpritePath.values().length);
        this.mapType = SpritePath.values()[chosen];
    }
    
    //Sets x and y sizes for the type chosen on object.
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
    
    //Chooses a random size from size array
    private void setSize() {
        int chosen = new Random().nextInt(this.xSizes.length);
        this.size = new int[] {this.xSizes[chosen], this.ySizes[chosen]};
    }
   
    /**
     * 
     * @return Width of object as float
     */
    public float getXSize() {
        return this.size[0];
    }
    
    /**
     * 
     * @return Height of object as float
     */
    public float getYSize() {
        return this.size[1];
    }
    
    /**
     * @return x coordinate of object as float
     */
    public float getXCoor() {
        return xCoor;
    }

    /**
     * @return y coordinate of object as float
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
    
    /**
     * 
     * @return SpritePath for mapobject.
     */
    public SpritePath getMapType() {
        return this.mapType;
    }
}