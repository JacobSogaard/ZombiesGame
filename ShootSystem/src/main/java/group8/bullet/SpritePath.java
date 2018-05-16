package group8.bullet;

/**
 * Enum class to hold bullet imagepath in all directions.
 * @author group 8
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
