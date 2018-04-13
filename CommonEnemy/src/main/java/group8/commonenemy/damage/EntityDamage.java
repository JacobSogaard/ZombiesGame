/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commonenemy.damage;

/**
 * Class used to set how much damage and enemy can give to other entities. 
 * @author jacob
 */
public class EntityDamage {
    private float damagepoint;
    
    public EntityDamage(float damagepoint){
        this.damagepoint = damagepoint;
    }

    /**
     * @return the damagepoint
     */
    public float getDamagepoint() {
        return damagepoint;
    }

    /**
     * @param damagepoint the damagepoint to set
     */
    public void setDamagepoint(float damagepoint) {
        this.damagepoint = damagepoint;
    }
}
