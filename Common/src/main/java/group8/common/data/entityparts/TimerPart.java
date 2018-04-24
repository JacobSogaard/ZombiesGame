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
 * @author Alexander
 */
public class TimerPart
        implements EntityPart {

    private float expiration;

    public TimerPart(int expiration) {
        this.expiration = expiration;
    }

    public float getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void reduceExpiration(int i) {
        this.expiration -= i;
    }
    
    public void reduceEx(int time) {
        this.expiration -= time;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        if (expiration > 0) {
//            reduceExpiration(gameData.getDelta());
              reduceEx(1);
        }
    }
    
}
