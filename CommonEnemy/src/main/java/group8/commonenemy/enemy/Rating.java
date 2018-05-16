/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commonenemy.enemy;

/**
 * Universal, ascending enemy rating system.
 * All enemy types must be instantiated with a rating to let the game (EnemyWave)
 * know, how difficult it is to defeat.
 * @author group8
 */
public enum Rating {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), MINIBOSS(11), BOSS(12);
     
    Rating(int i) {
        this.i = i;
    }
    
    private int i;
    
    public int getIntValue() {
        return this.i;
    }
    
    public static int getMaxValue() {
        int max = 0;
        for (Rating r : Rating.values()) {
            if (r.getIntValue() > max)
                max = r.getIntValue();
        }
        
        return max;
    }
}
