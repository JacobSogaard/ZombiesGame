/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.services;

import group8.common.data.Entity;
import group8.common.data.GameKeys;
import java.util.Map;

/**
 *
 * @author jacob
 */
public interface ICanMoveService {
    public Map<Entity, Integer> canMoveUp(Entity entity);
    public Map<Entity, Integer> canMoveDown(Entity entity);
    public Map<Entity, Integer> canMoveLeft(Entity entity);
    public Map<Entity, Integer> canMoveRight(Entity entity);
}
