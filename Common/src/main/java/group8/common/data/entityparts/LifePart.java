package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;

/**
 * Class used handle how much life an entity has, and to check if an entity dies.
 * @author group8
 */
public class LifePart implements EntityPart {
    private boolean dead = false;
    private int life;
    private boolean isHit = false;

    /**
     * Contructor used to set how much life an entity should have.
     * @param life 
     */
    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public boolean isDead() {
        return dead;
    }

    
    
    @Override
    public void process(GameData gameData, Entity entity) {
        if (isHit) {
            life =- 1;
            isHit = false;
        }
        if (life <= 0) {
            dead = true;
        }
    }
}
