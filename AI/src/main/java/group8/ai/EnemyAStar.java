package group8.ai;

import group8.common.data.Entity;
import group8.common.data.GameKeys;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.mapcommon.IMapCollision;
import java.util.*;
import org.openide.util.Lookup;

/**
 * Class to handle A star search. Takes the player position, and finds the
 * optimal path. Sets the goal position when called using the players current
 * pos.
 *
 * @author group8
 */
public class EnemyAStar {

    private Entity enemy, player;
    private List<Integer> result;
    private float goalX, goalY, enemyX, enemyY, successorX, successorY, enemySpeed;
    private List<Float> successorUp, successorDown, successorLeft, successorRight;
    private Lookup lookup = Lookup.getDefault();
    protected IMapCollision map = lookup.lookup(IMapCollision.class);
    private Integer previousDir = GameKeys.SHIFT;
    private List<Entity> mapObjects;

    /**
     * Init class and state space
     *
     * @param enemy
     * @param player
     */
    public EnemyAStar(Entity enemy, Entity player) {

        this.enemy = enemy;
        this.player = player;
        this.result = new ArrayList();
        this.successorUp = new ArrayList();
        this.successorLeft = new ArrayList();
        this.successorRight = new ArrayList();
        this.successorDown = new ArrayList();
        //Init goal and enemy positions
        PositionPart playerPos = player.getPart(PositionPart.class);
        PositionPart enemyPos = enemy.getPart(PositionPart.class);
        MovingPart enemyMove = enemy.getPart(MovingPart.class);
        this.goalX = playerPos.getX();
        this.goalY = playerPos.getY();
        this.enemyX = enemyPos.getX();
        this.enemyY = enemyPos.getY();
        this.enemySpeed = enemyMove.getSpeed();
    }

    /**
     * Get list of directions to target. Makes recursive calls to next successor
     *
     * @return List of Integers
     */
    public List<Integer> getResult() {
        this.result.clear();
        if (this.mapObjects == null) {
            this.mapObjects = this.map.getMapObjects();
        }
       
        while (!this.enemyInRange()) {
            this.getNextSuccessor();
        }

        return this.result;
    }

    //Method to set all successor lists
    private void setSuccessors() {
        //Successor Up
        this.successorUp.clear();
        this.successorUp.add(this.enemyX);
        this.successorUp.add(this.enemyY + this.enemySpeed);

        //Successor Down
        this.successorDown.clear();
        this.successorDown.add(this.enemyX);
        this.successorDown.add(this.enemyY - this.enemySpeed);

        //Successor left
        this.successorLeft.clear();
        this.successorLeft.add(this.enemyX - this.enemySpeed);
        this.successorLeft.add(this.enemyY);

        //Successor right
        this.successorRight.clear();
        this.successorRight.add(this.enemyX + this.enemySpeed);
        this.successorRight.add(this.enemyY);
    }

    //Method to get the successor of current entity position
    private void getNextSuccessor() {
        this.setSuccessors();

        Map<Integer, List<Float>> successors = new HashMap();
        successors.put(GameKeys.UP, this.successorUp);
        successors.put(GameKeys.DOWN, this.successorDown);
        successors.put(GameKeys.LEFT, this.successorLeft);
        successors.put(GameKeys.RIGHT, this.successorRight);

        //Init next direction, should always be set in map iteration, but if not, is set to UP
        Integer successorDir = GameKeys.UP;

        //Iterator 
        Iterator it = successors.entrySet().iterator();

        //Init dist, set at max value to ensure first direction is the best distance
        float dist = Float.MAX_VALUE;

        while (it.hasNext()) {
            Map.Entry successor = (Map.Entry) it.next();
            List<Float> successorValue = (List<Float>) successor.getValue();
            float calculatedDist = this.calcDist(successorValue.get(0), successorValue.get(1));

            //If next straight line distance is shorter than previous, set this at the new.
            if (dist >= calculatedDist && !GameKeys.isOpposite((Integer) successor.getKey(), this.previousDir)
                    && !this.isMapCollision(successorValue.get(0), successorValue.get(1))) {
                dist = calculatedDist;
                this.successorX = successorValue.get(0);
                this.successorY = successorValue.get(1);
                successorDir = (Integer) successor.getKey();
            }
        }

        this.setEnemyCoordinates();
        this.result.add(successorDir);
        this.previousDir = successorDir;

    }

    //Sets the enemy coordinates to the found successor coordinates
    private void setEnemyCoordinates() {
        this.enemyX = this.successorX;
        this.enemyY = this.successorY;
    }

    //Method that returns the distance between to points
    private float calcDist(float enemyX, float enemyY) {
        float dist = (float) Math.abs(Math.sqrt(Math.pow(this.goalX - enemyX, 2) + Math.pow(goalY - enemyY, 2)));
        return dist;
    }

    //Return true if the enemy is in range of the goal considering the enemy speed
    private boolean enemyInRange() {
        return this.enemyX >= this.goalX - 10.0f && this.enemyX <= this.goalX + 10.0f
                && this.enemyY >= this.goalY - 10.0f && this.enemyY <= this.goalY + 10.0f;
    }

    //Returns true if map object is in range of the enemy. 
    private boolean mapInRange(float[] enemy, float[] map) {
        return enemy[0] < map[0] + map[2] + 5.0f
                && enemy[0] + enemy[2] > map[0] - 5.0f
                && enemy[1] < map[1] + map[3] + 5.0f
                && enemy[1] + enemy[3] > map[1] - 5.0f;
        
    }

    //Iterates through map objects and checks if x and y coordinates parsed as 
    //parameters would be a collision with that object
    private boolean isMapCollision(float x, float y) {
        boolean col = false;
        float[] enemyRect = {x, y, this.enemy.getWidth(), this.enemy.getHeight()};
        for (Entity mapObject : this.mapObjects) {
            PositionPart mapPosition = mapObject.getPart(PositionPart.class);
            float[] mapRect = {mapPosition.getX(), mapPosition.getY(), mapObject.getWidth(), mapObject.getHeight()};
            if (this.mapInRange(enemyRect, mapRect)) {
                return true;
            }

        }

        return col;
    }

}
