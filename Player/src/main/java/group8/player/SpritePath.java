/*
 
 */
package group8.player;

/**
 * 
 * @author group8
 */
public enum SpritePath {
    
    UP("Images/PlayerImages/UP.png"), DOWN("Images/PlayerImages/DOWN.png"), 
    LEFT("Images/PlayerImages/LEFT.png"), RIGHT("Images/PlayerImages/RIGHT.png"), 
    UPRIGHT("Images/PlayerImages/UPRIGHT.png"), UPLEFT("Images/PlayerImages/UPLEFT.png"),
    DOWNRIGHT("Images/PlayerImages/DOWNRIGHT.png"), DOWNLEFT("Images/PlayerImages/DOWNLEFT.png");
    
    private final String path;
    
    private SpritePath(String p) {
        this.path = p;
    }
    
    @Override
    public String toString() {
        return this.path;
    }
}
