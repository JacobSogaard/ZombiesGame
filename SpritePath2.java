/*
 
 */
package group8.player;

/**
 * 
 * @author group8
 */
public enum SpritePath2 {
    
    UP("/Users/matiasmarek/ZombiesGame/Player/src/main/resources/group8/player/Sprites/test.jpg"), DOWN("path"), LEFT("path"), RIGHT("path"), UPRIGHT("path"),
    UPLEFT("path"), DOWNRIGHT("path"), DOWNLEFT("path");
    
    private final String path;
    
    private SpritePath2(String p) {
        this.path = p;
    }
    
    @Override
    public String toString() {
        return this.path;
    }
}
