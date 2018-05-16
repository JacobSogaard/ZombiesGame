package group8.weapon;

import group8.common.data.Entity;
import group8.common.data.EntityType;

/**
 * Constructor class for weapon. Sets the entity type to weapon and hold an int array
 * for bullet attributes and a string array for spritepaths. 
 * @author group 8
 */
public class Weapon extends Entity {
    private int[] bulletAttributes;
    private String[] bulletSpritePaths;
    
    /**
     * Constructor. Sets entitytype to WEAPON
     */
    public Weapon() {
        this.setType(EntityType.WEAPON);
    }

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
