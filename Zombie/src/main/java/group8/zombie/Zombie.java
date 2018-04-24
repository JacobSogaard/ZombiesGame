/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.entityparts.PositionPart;
import group8.common.playercommon.IPlayerService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.EntityDamage;
import group8.commonenemy.enemy.Rating;
import group8.commonenemy.services.IPathFinderService;
import java.util.Random;
import org.openide.util.Lookup;

/**
 * Super class for zombies. Only sets the entity type to be a zombie.
 * @author jacob
 */
public abstract class Zombie extends Enemy {
    private EntityDamage damage;
    private Random r = new Random();

    
    public Zombie(Rating rating) {
        super(rating);

        this.setType(EntityType.ZOMBIE);
    }
    
    //Method to set the x value of a new zombie with defined distance from player
    public float setX(GameData gameData, float distance){
        float playerX = this.getPlayerPos().getX();
        float x = r.nextInt((int) (((gameData.getDisplayWidth()) - (gameData.getDisplayWidth() / 2)) 
                + playerX));
        
        //If zombie is too close to player, move it.
        if (x >= playerX) {
            x += distance;
        } else {
            x -= distance;
        }
        
        //If zombie is over one of the edges move it inside
        if (x < 40) {
            x = 40;
        }
        
        if (x > 1500) {
            x = 1500;
        }
        
        return x;
    }
    
    //Method to set the y value of a new zombie with defined distance from player
    public float setY(GameData gameData, float distance){
         float playerY = this.getPlayerPos().getY();
        
        float y = r.nextInt((int) (((gameData.getDisplayHeight()) - (gameData.getDisplayHeight() / 2)) 
                + playerY));
        
        //If zombie is too close to player, move it.
        if (y >= playerY) {
            y += distance;
        } else {
            y -= distance;
        }
        
        //If zombie is over one of the edges move it inside
        if (y < 40) {
            y = 40;
        }
        
        if (y > 1100) {
            y = 1100;
        }
        return y;
    }
    
    private PositionPart getPlayerPos(){
        Lookup lookup = Lookup.getDefault();
        IPlayerService ps = lookup.lookup(IPlayerService.class);
        Entity player = ps.getPlayer();
        return player.getPart(PositionPart.class);
    }
    
}
