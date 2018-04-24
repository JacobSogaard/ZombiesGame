/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.ai;

import group8.common.data.Entity;
import group8.common.data.GameKeys;
import group8.common.playercommon.PlayerServiceImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kasper
 */
public class EnemyMovement {
    private final PlayerServiceImpl pimpl = PlayerServiceImpl.getInstance();
    private Map<Integer, Boolean> directions;
    
    public Map<Integer, Boolean> getDirections(Entity enemy) {
        this.fillDirections(enemy, pimpl.getPlayer());
        return this.directions;
    }
    
    private void fillDirections(Entity enemy, Entity player) {
        this.directions = new HashMap();
        for (int i = 0; i <= 3; i++) {
            directions.put(i, false);
        }
        
        float[] enemyCenter = getCenter(enemy.getShapeX(), enemy.getShapeY());
        float[] playerCenter = getCenter(player.getShapeX(), player.getShapeY());
        
        if (enemyCenter[0] < playerCenter[0])
            directions.put(GameKeys.RIGHT, true);
        else if (enemyCenter[0] > playerCenter[0])
            directions.put(GameKeys.LEFT, true);
        else if (enemyCenter[1] < playerCenter[1])
            directions.put(GameKeys.UP, true);
        else if (enemyCenter[1] > playerCenter[1])
            directions.put(GameKeys.DOWN, true);
        
    }
    
    private float[] getCenter(float[] shapeX, float[] shapeY) {
        float xMax = 0;
        for (float p : shapeX) {
            xMax += p;
        }
        
        float yMax = 0;
        for (float p : shapeY) {
            yMax += p;
        }
        
        float xCenter = xMax / 4;
        float yCenter = yMax / 4;
        
        float[] center = {xCenter, yCenter};
        return center;
    }
}
