/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.common.mapcommon;

import group8.common.data.Entity;
import java.util.ArrayList;

/**
 *
 * @author group8
 */
public interface IMapCollision {
    //Implemented by MapObejctPlugin. Used for getting all mapobjects.
   public ArrayList<Entity> getMapObjects();
    
}
