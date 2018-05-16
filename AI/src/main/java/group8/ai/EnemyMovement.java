/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.ai;

import group8.common.data.Entity;
import group8.common.data.GameKeys;
import group8.common.playercommon.PlayerServiceImpl;
import group8.commonenemy.services.IPathFinderService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author group8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IPathFinderService.class)})
public class EnemyMovement implements IPathFinderService{
    private final PlayerServiceImpl pimpl = PlayerServiceImpl.getInstance(); 
    private Map<Integer, Boolean> directions;
    
    /**
     * Method for getting the possible directions for enemy.
     * @param enemy
     * @return Map with Integer as key and Boolean as value
     */
    @Override
    public Map<Integer, Boolean> getDirections(Entity enemy) {
        this.fillDirections(enemy, pimpl.getPlayer());
        return this.directions;
    }
    
    //Fills the directions map with the possible directions the enemy can move.
    private void fillDirections(Entity enemy, Entity player) {
        this.directions = new HashMap();
        for (int i = 0; i <= 3; i++) {
            directions.put(i, false);
        }
        
        float[] enemyCenter = getCenter(enemy.getShapeX(), enemy.getShapeY());
        float[] playerCenter = getCenter(player.getShapeX(), player.getShapeY());
        
        if (enemyCenter[0] < playerCenter[0])
            directions.put(GameKeys.RIGHT, true);
        if (enemyCenter[0] > playerCenter[0])
            directions.put(GameKeys.LEFT, true);
        if (enemyCenter[1] < playerCenter[1])
            directions.put(GameKeys.UP, true);
        if (enemyCenter[1] > playerCenter[1])
            directions.put(GameKeys.DOWN, true);
        
    }
    
    //Gets center of a enemy
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

    /**
     * Method for creating and adding the artificial intelligence AStar to an enemy. 
     * Implements IPathFinderService.
     * @param enemy
     * @return List of Integers
     */
    @Override
    public List<Integer> AStarDirections(Entity enemy) {
        EnemyAStar astar = new EnemyAStar(enemy, pimpl.getPlayer());
        return astar.getResult();
    }
}
