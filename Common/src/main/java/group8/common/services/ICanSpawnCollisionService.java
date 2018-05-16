/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;

/**
 *
 * @author matiasmarek
 */
public interface ICanSpawnCollisionService {

    public void placeMeWhereICanSpawn(Entity entity1, Entity entity2);
}
