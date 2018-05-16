/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commonenemy.services;

import group8.common.data.GameData;
import group8.common.data.World;
import group8.commonenemy.enemy.Rating;

/**
 *
 * @author kasper
 */
public interface IEnemyPluginService {
    void start(GameData gameData, World world);
    void stop(GameData gameData, World world);
    Rating getRating();
}
