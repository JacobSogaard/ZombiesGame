/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commonenemy.enemy;

import group8.common.data.Entity;

/**
 *
 * @author kasper
 */
public class Enemy extends Entity {
    private final Rating rating;
    
    public Enemy(Rating rating) {
        this.rating = rating;
    }
    
    public Rating getRating() {
        return this.rating;
    }
}
