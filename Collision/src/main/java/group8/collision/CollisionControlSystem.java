/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.GameKeys;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.mapcommon.IMapCollision;
import group8.common.playercommon.IPlayerService;
import group8.common.services.IEntityProcessingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import group8.common.services.ICollisionRequestService;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
    ,
    @ServiceProvider(service = ICollisionRequestService.class)

})
public class CollisionControlSystem implements IEntityProcessingService, ICollisionRequestService {

    private enum DIRECTION {
        UP, DOWN, LEFT, RIGHT;
    }
    
    private Map directionMap = new HashMap();

    @Override
    public void process(GameData gameData, World world) {

    }

    //Method to get rectangle from an entity
    private float[] getEntityRect(Entity entity) {
        float x = entity.getShapeX()[0];
        float y = entity.getShapeY()[0];
        float width = entity.getShapeX()[3] - x;
        float height = entity.getShapeY()[1] - y;
        float[] entityRect = {x, y, width, height};
        return entityRect;
    }


    private boolean isSameEntity(Entity e1, Entity e2) {
        return e1.getID().equals(e2.getID());

    }

    private void checkCollision(Entity entity, World world) {
        float[] entity1Rect = this.getEntityRect(entity);
        for (Entity e : world.getEntities()) {
            if (!this.isSameEntity(e, entity)) {
                float[] entity2Rect = this.getEntityRect(e);

                if (entity1Rect[0] < entity2Rect[0] + entity2Rect[2]
                        && entity1Rect[0] + entity1Rect[2] > entity2Rect[0]
                        && entity1Rect[1] < entity2Rect[1] + entity2Rect[3]
                        && entity1Rect[3] + entity1Rect[1] > entity2Rect[1]) {
                    this.setCollisionDir(entity, e);
                }
            }
        }
    }

//    private Entity downCollision(Entity entity) {
//        float[] entity1Rect = this.getEntityRect(entity);
//
//        for (Entity e : this.world.getEntities()) {
//            if (!this.isSameEntity(e, entity)) {
//                float[] entity2Rect = this.getEntityRect(e);
//
//                if (entity1Rect[1] < entity2Rect[1] + entity2Rect[3]) {
//                    System.out.println("DOWN");
//                    return e;
//                }
//            }
//        }
//        return this.noneEntity();
//    }
//
//    private Entity leftCollision(Entity entity) {
//        float[] entity1Rect = this.getEntityRect(entity);
//
//        for (Entity e : this.world.getEntities()) {
//            if (!this.isSameEntity(e, entity)) {
//                float[] entity2Rect = this.getEntityRect(e);
//                if (entity1Rect[0] < entity1Rect[0] + entity2Rect[2]) {
//                    System.out.println("LEFT");
//                    return e;
//                }
//            }
//        }
//        return this.noneEntity();
//    }
//
//    private Entity rightCollision(Entity entity) {
//        float[] entity1Rect = this.getEntityRect(entity);
//
//        for (Entity e : this.world.getEntities()) {
//            if (!this.isSameEntity(e, entity)) {
//                float[] entity2Rect = this.getEntityRect(e);
//
//                if (entity1Rect[0] + entity1Rect[2] > entity2Rect[0]) {
//                    System.out.println("RIGHT");
//                    return e;
//                }
//            }
//        }
//        return this.noneEntity();
//    }
    private void setCollisionDir(Entity entity1, Entity entity2) {

        float entity1Up = entity1.getShapeY()[1];
        float entity1Down = entity1.getShapeY()[0];
        float entity1Left = entity1.getShapeX()[0];
        float entity1Right = entity1.getShapeX()[2];

        float entity2Up = entity2.getShapeY()[1];
        float entity2Down = entity2.getShapeY()[0];
        float entity2Left = entity2.getShapeX()[0];
        float entity2Right = entity2.getShapeX()[2];

        this.directionMap.put(DIRECTION.UP, true);
        this.directionMap.put(DIRECTION.DOWN, true);
        this.directionMap.put(DIRECTION.LEFT, true);
        this.directionMap.put(DIRECTION.RIGHT, true);

        //Up collision
        if ((entity1Up >= entity2Down) && ((entity1Right > entity2Left || entity1Left < entity2Right)
                || (entity2Right > entity1Left || entity2Left < entity1Right))) {
            this.directionMap.put(DIRECTION.UP, false);
        }

        //Down collision
        if (entity1Down <= entity2Up && ((entity1Right > entity2Left || entity1Left < entity2Right)
                || (entity2Right > entity1Left || entity2Left < entity1Right))) {
            this.directionMap.put(DIRECTION.DOWN, false);
        }

        //Left collision
        if (entity1Left <= entity2Right && ((entity1Up > entity2Down || entity1Down < entity2Up)
                || (entity2Up > entity1Down || entity2Down < entity1Up))) {
            this.directionMap.put(DIRECTION.LEFT, false);
        }

        //Right collision
        if (entity1Right >= entity2Left && ((entity1Up > entity2Down || entity1Down < entity2Up)
                || (entity2Up > entity1Down || entity2Down < entity1Up))) {
            this.directionMap.put(DIRECTION.RIGHT, false);
        }
    }

    private boolean getCanMove(DIRECTION dir, Entity entity, World world) {
        this.checkCollision(entity, world);
        return (Boolean) this.directionMap.get(dir);

    }

    @Override
    public boolean canMoveUp(Entity entity, World world) {
        return this.getCanMove(DIRECTION.UP, entity, world);
    }

    @Override
    public boolean canMoveDown(Entity entity, World world) {
        return this.getCanMove(DIRECTION.DOWN, entity, world);
    }

    @Override
    public boolean canMoveLeft(Entity entity, World world) {
        return this.getCanMove(DIRECTION.LEFT, entity, world);
    }

    @Override
    public boolean canMoveRight(Entity entity, World world) {
        return this.getCanMove(DIRECTION.DOWN, entity, world);
    }
}
