
package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;

/**
 * Class that handles entities movement.
 * @author Group 8 
 */
public class MovingPart implements EntityPart {

    private float dx, dy;
    private float speed;
    private boolean left, right, up, down;
    //This array is used to handle which direction an entity face.
    private boolean[] directions = new boolean[8];
    
    /**
     * Constructor that is used to set how fast an entity should move.
     * @param maxSpeed 
     */
    public MovingPart(float maxSpeed) {
        this.speed = maxSpeed;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }


    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    public float getSpeed(){
        return this.speed; 
    }
    
    public void setSpeed(float newSpeed){
        this.speed = newSpeed;         
    }
    
    /**
     * Method used for setting directions array.
     * @param direction 
     */
    public void setDirection(int direction) {
        for(int i = 0; i < this.directions.length; i++) {
            this.directions[i] = i == direction;
        }
    }
    
    public boolean[] getDirection() {
        return this.directions;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float dt = gameData.getDelta();
        float tempSpeed = speed;
        
        //Checks if the player is moving diagonally
        if(isUp() && isRight() || isUp() && isLeft() || isDown() && isRight() || isDown() && isLeft()){
            tempSpeed *= 0.667;
        }
        
        //Checks which button player is pressing.
        if (isUp()) {
            y += tempSpeed;
            
        }
        if (isRight()) {
            x += tempSpeed;
        }
        if(isDown()){
            y -= tempSpeed; 
        }
        
        if(isLeft()){
            x -= tempSpeed; 
        }

        //Backup for Collision
        //Stops entities for moving out of the map and make it stop moving.
        if (x >= gameData.getDisplayWidth()*2-entity.getWidth()) {
            x -= tempSpeed;
        } else if (x < 0) {
            x += tempSpeed; 
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()*2-entity.getHeight()) {
            y -= tempSpeed;
        } else if (y < 0) {
            y += tempSpeed;
        }
        
        positionPart.setX(x);
        positionPart.setY(y);

    }

    /**
     * @return the left
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public boolean isRight() {
        return right;
    }

    /**
     * @return the up
     */
    public boolean isUp() {
        return up;
    }

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }
}
