
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;

/**
 *
 * @author group8
 */
public interface ISpawnService {
    //Method implemented by CollisionControlSystem, used for spwaning entity.
    Entity spawnHere(Entity e, GameData gameData, World world);
}
