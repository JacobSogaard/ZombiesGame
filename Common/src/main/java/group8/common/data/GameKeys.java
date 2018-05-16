package group8.common.data;

/**
 * 
 * @author group8
 */
public class GameKeys {

    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS = 8;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int ENTER = 4;
    public static final int ESCAPE = 5;
    public static final int SPACE = 6;
    public static final int SHIFT = 7;

    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];

    }
    
    public static boolean isOpposite(int key1, int key2){
        switch(key1){
            case UP:
                return key1 + 2 == key2;
            case DOWN:
                return key1 - 2 == key1;
            case LEFT:
                return key1 + 2 == key2;
            case RIGHT:
                return key1 - 2 == key2;
            default:
                return false;
        }         
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public void setKey(int k, boolean b) {
        keys[k] = b;
    }

    public boolean isDown(int k) {
        return keys[k];
    }

    public boolean isPressed(int k) {
        return keys[k] && !pkeys[k];
    }

}
