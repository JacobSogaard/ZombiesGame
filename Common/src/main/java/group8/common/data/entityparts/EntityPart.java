/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.data.entityparts;

import group8.common.data.Entity;
import group8.common.data.GameData;

/**
 *
 * @author group8
 */
public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
