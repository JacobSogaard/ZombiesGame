/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.commonenemy.damage.EntityDamage;

/**
 * Super class for zombies. Only sets the entity type to be a zombie.
 * @author jacob
 */
public class Zombie extends Entity {
    private EntityDamage damage;
    
    public Zombie () {
        this.setType(EntityType.ZOMBIE);
    }
    
    
    
}
