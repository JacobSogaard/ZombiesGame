package group8.common.services;

import group8.common.data.GameData;
import group8.common.data.World;

/**
 * Interface used to call process methods on entities.
 * @author group8
 */
public interface IEntityProcessingService {

    void process(GameData gameData, World world);
    
}
