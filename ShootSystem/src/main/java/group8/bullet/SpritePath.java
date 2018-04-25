/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.bullet;

/**
 *
 * @author MER
 */
public enum SpritePath {

    UP("Images/BulletImages/UP.png"), DOWN("Images/BulletImages/DOWN.png"), 
    LEFT("Images/BulletImages/LEFT.png"), RIGHT("Images/BulletImages/RIGHT.png");
    
    private final String path;
    
    private SpritePath(String p) {
        this.path = p;
    }
    
    @Override
    public String toString() {
        return this.path;
    }
    
}
