/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.GameKeys;
import group8.common.data.World;
import java.util.Map;

/**
 *
 * @author jacob
 */
public interface ICollisionRequestService {
    //public Entity collisionRequest(Entity entity, World world);
    public boolean canMoveUp(Entity entity, World world);
    public boolean canMoveDown(Entity entity, World world);
    public boolean canMoveLeft(Entity entity, World world);
    public boolean canMoveRight(Entity entity, World world);
}
