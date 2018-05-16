package group8.player;


import group8.common.data.Entity; 
import group8.common.data.EntityType;

/**
 * Constructor for player entity. Extends Entity.
 * @author group 8
 */
public class Player extends Entity{
    
    /**
     * Constructor. Sets the EntityType to PLAYER
     */
    public Player () {
        this.setType(EntityType.PLAYER);
    }
}
