package group8.mapobject;

/**
 * Enum class to hold spritepaths for mapobjects. 
 * @author group 8 
 */
public enum SpritePath {
    
    TREE1("Images/MapObjects/Tree1.png"), 
    TREE2("Images/MapObjects/Tree2.png"),
    ROCK1("Images/MapObjects/Rock1.png");
    
    private final String path;
    
    private SpritePath(String p) {
        this.path = p;
    }
    
    @Override
    public String toString() {
        return this.path;
    }
}
