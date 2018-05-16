
package group8.commonenemy.services;

import group8.common.data.Entity;
import java.util.List;
import java.util.Map;

/**
 * Interface implemented by EnemyMovement. Used to set intelligence on enemies.
 * @author group8
 */
public interface IPathFinderService {
    Map<Integer, Boolean> getDirections(Entity enemy);
    List<Integer> AStarDirections(Entity enemy);
}
