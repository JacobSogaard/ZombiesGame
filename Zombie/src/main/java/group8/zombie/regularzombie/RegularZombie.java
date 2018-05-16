package group8.zombie.regularzombie;

import group8.commonenemy.enemy.Rating;
import group8.zombie.Zombie;

/**
 * Super class for regular zombie, extends Zombie
 * @author group 8
 */
public class RegularZombie extends Zombie {

    /**
     * Constructor for regularZombie
     * @param rating The rating the zombie should have of type Rating
     */
    public RegularZombie(Rating rating){
        super(rating);
    }
    

    
}
