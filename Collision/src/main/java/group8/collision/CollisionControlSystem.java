/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.mapcommon.IMapCollision;
import group8.common.services.IEntityProcessingService;
import java.util.ArrayList;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})
public class CollisionControlSystem implements IEntityProcessingService {

    private ArrayList<Entity> mapObjects;
    private final Lookup lookup = Lookup.getDefault();
    private boolean hasCheckedMapObjects = false;

    @Override
    public void process(GameData gameData, World world) {
        this.getMapObjects();
        for (Entity m : mapObjects) {
            
        }
    }

    private void getMapObjects() {
        if (!hasCheckedMapObjects) {
            Lookup.Result<IMapCollision> map = lookup.lookupResult(IMapCollision.class);
            for (IMapCollision mapCollision : map.allInstances()) {
                this.mapObjects = mapCollision.getMapObjects();
            }
            this.hasCheckedMapObjects = true;
        }
    }

    //Method to check collision between two entities
    private boolean isCollision(Entity entity1, Entity entity2) {
        //x, y, height and width for entity1
        float x1 = entity1.getShapeX()[0];
        float y1 = entity1.getShapeY()[0];
        float width1 = entity1.getShapeX()[3] - x1;
        float height1 = entity1.getShapeY()[1] - y1;
        float[] entity1Rect = {x1, y1, width1, height1};

        //x, y, height and width for entity2
        float x2 = entity2.getShapeX()[0];
        float y2 = entity2.getShapeY()[0];
        float width2 = entity2.getShapeX()[3] - x2;
        float height2 = entity2.getShapeY()[1] - y2;
        float[] entity2Rect = {x2, y2, width2, height2};

        //Collision check
        if (entity1Rect[0] < entity2Rect[0] + entity2Rect[2]
                && entity1Rect[0] + entity1Rect[2] > entity2Rect[0]
                && entity1Rect[1] < entity2Rect[1] + entity2Rect[3]
                && entity1Rect[3] + entity1Rect[1] > entity2Rect[1]) {
            return true;
        }
        return false;
    }

}
