/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commoncollision;

import group8.common.data.Entity;

/**
 *
 * @author matiasmarek
 */
public interface IStandardCollisionService {
  public void detectStandardCollision(Entity e1, Entity e2);
}