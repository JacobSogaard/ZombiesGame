
package group8.commonenemy.enemy;

import group8.common.data.Entity;

/**
 * Abstract class to contain all enemies.
 * @author group8
 */
public abstract class Enemy extends Entity {
    protected final Rating rating;
    
    
    public Enemy(Rating rating) {
        this.rating = rating;
    }
    
    public Rating getRating() {
        return this.rating;
    }
}
