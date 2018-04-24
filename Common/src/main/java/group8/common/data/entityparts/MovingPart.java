/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;
import static group8.common.data.GameKeys.LEFT;
import static group8.common.data.GameKeys.RIGHT;
import static group8.common.data.GameKeys.UP;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author Group 8 
 */
public class MovingPart implements EntityPart {

    private float dx, dy;
    private float speed;
    private boolean[] directions;
    private boolean left, right, up, down;

    public MovingPart(float maxSpeed) {
        this.speed = maxSpeed;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }


    public boolean setLeft(boolean left) {
        this.left = left;
        return this.left;
    }

    public boolean setRight(boolean right) {
        this.right = right;
        return this.right;
    }

    public boolean setUp(boolean up) {
        this.up = up; 
        return this.up; 
    }

    public boolean setDown(boolean down) {
        this.down = down; 
        return this.down; 
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
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
        
        
        
         /*
        FIXTHIS!!!!!!!!!!!!!!!!!!!!!
        */
        //Backup for Collision
        //Stops the player for moving out of the map and make him stand still.
        if (x >= gameData.getDisplayWidth()*2-40) {
            x -= tempSpeed;
        } else if (x < 0) {
            x += tempSpeed; 
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()*2-70) {
            y -= tempSpeed;
        } else if (y < 0) {
            y += tempSpeed;
        }


        positionPart.setX(x);
        positionPart.setY(y);
        

        positionPart.setRadians(radians);
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
