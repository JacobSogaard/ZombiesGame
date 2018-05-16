package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;

/**
 * Class used to handle an entity's position. Holds x and y coordinates.
 * @author group8
 */
public class PositionPart implements EntityPart {

    private float x;
    private float y;
    
    /**
     * Constructor used to set an entity's coordinates.
     * @param x
     * @param y 
     */
    public PositionPart(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public void setX(float newX) {
        this.x = newX;
    }
    
    public void setY(float newY) {
        this.y = newY;
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
    
}
