package group8.common.services;

import group8.common.data.GameData;
import group8.common.data.World;

public interface IEntityProcessingService {

    void process(GameData gameData, World world);
}
