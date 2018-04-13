/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.mapobject;

/**
 *
 * @author matiasmarek
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
