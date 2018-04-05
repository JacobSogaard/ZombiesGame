/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.player;


import group8.common.data.Entity; 
import group8.common.data.EntityType;

/**
 *
 * @author matiasmarek
 */
public class Player extends Entity{
    public Player () {
        this.setType(EntityType.PLAYER);
    }
}
