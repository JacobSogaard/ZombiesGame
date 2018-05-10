/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;

/**
 *
 * @author kasper
 */
public interface ISpawnService {
    Entity spawnHere(Entity e, GameData gameData, World world);
}
