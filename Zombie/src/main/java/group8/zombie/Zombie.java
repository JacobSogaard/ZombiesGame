/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.playercommon.IPlayerService;
import group8.commonenemy.enemy.Enemy;
import group8.commonenemy.enemy.EntityDamage;
import group8.commonenemy.enemy.Rating;
import group8.commonenemy.services.IPathFinderService;

/**
 * Super class for zombies. Only sets the entity type to be a zombie.
 * @author jacob
 */
public abstract class Zombie extends Enemy {
    private EntityDamage damage;

    
    public Zombie(Rating rating) {
        super(rating);

        this.setType(EntityType.ZOMBIE);
    }
    
}
