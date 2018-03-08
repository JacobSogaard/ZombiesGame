package group8.common.services;

import group8.common.data.GameData;
import group8.common.data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
}
