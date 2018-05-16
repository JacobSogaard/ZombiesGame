package group8.zombie.smallzombie;

import group8.commonenemy.enemy.Rating;
import group8.zombie.Zombie;


/**
 * Super class for small zombie, extends Zombie
 * @author group 8
 */
public class SmallZombie extends Zombie {
    
    /**
     * Constructor for smallZombie
     * @param rating The rating the zombie should have of type Rating
     */
    public SmallZombie(Rating rating){
        super(rating);
    }
}
