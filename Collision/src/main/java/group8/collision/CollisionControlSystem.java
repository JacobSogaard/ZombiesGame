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

    private World world;

    @Override
    public void process(GameData gameData, World world) {
        this.world = world;
        //this.checkCollision();

    }
    
     @Override
    public Entity collisionRequest(Entity entity, int direction) {
        switch (direction){
            case GameKeys.UP:
                return this.upCollision(entity);
            case GameKeys.LEFT:
                return this.leftCollision(entity);
            case GameKeys.DOWN:
                return this.downCollision(entity);
            case GameKeys.RIGHT:
                return this.rightCollision(entity);
            default:
                return this.noneEntity();
        }
    }
    

    private void checkCollision() {
        
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

    private Entity noneEntity(){
        Entity noneEntity = new Entity();
        noneEntity.setType(EntityType.NONE);
        return noneEntity;
    }
    
    private Entity upCollision(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.world.getEntities()) {
            float[] entity2Rect = this.getEntityRect(e);

            if (entity1Rect[3] + entity1Rect[1] > entity2Rect[1]) {
                return e;
            }
        }
        
        return this.noneEntity();
    }
    
    
    
    private Entity downCollision(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.world.getEntities()) {
            float[] entity2Rect = this.getEntityRect(e);

            if (entity1Rect[1] < entity2Rect[1] + entity2Rect[3]) {
                return e;
            }
        }
        return this.noneEntity();
    }

    
    private Entity leftCollision(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.world.getEntities()) {
            float[] entity2Rect = this.getEntityRect(e);
            if (entity1Rect[0] < entity1Rect[0] + entity2Rect[2]) {
                return e;
            }
        }
        return this.noneEntity();
    }

    
    private Entity rightCollision(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.world.getEntities()) {
            float[] entity2Rect = this.getEntityRect(e);

            if (entity1Rect[0] + entity1Rect[2] > entity2Rect[0]) {
                return e;
            }
        }
        return this.noneEntity();
    }

   

}
