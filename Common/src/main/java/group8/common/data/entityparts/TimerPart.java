
package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;

/**
 * Class used to handle timers on entity's.
 * @author group8
 */
public class TimerPart
        implements EntityPart {

    private float expiration;
    
    /**
     * Contructor used for setting a timer on a entity.
     * @param expiration 
     */
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
            reduceEx(1);
        }
    }
    
}
