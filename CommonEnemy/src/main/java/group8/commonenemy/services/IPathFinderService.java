/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.commonenemy.services;

import group8.common.data.Entity;
import group8.common.data.GameKeys;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jacob
 */
public interface IPathFinderService {
    Map<Integer, Boolean> getDirections(Entity enemy);
    List<Integer> AStarDirections(Entity enemy);
}
