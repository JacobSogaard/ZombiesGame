/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;

/**
 *
 * @author MER
 */
public class DamagePart implements EntityPart {
    
    private int damage;
    
    @Override
    public void process(GameData gameData, Entity entity) {
    }
    
    public DamagePart(int damage){
        this.damage = damage;
    }

    /**
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    
}
