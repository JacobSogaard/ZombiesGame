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
import group8.common.services.ICanMoveService;
import group8.common.services.IEntityProcessingService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
    ,
    @ServiceProvider(service = ICanMoveService.class)

})
public class CollisionControlSystem implements IEntityProcessingService, ICanMoveService {

    private ArrayList<Entity> entities;

    @Override
    public void process(GameData gameData, World world) {
        this.entities.addAll(world.getEntities());
        this.checkCollision();

    }

    private void checkCollision() {
        for (Entity entity1 : this.entities) {
            EntityType entity1Type = entity1.getType();

            if (entity1Type.equals(EntityType.PLAYER)
                    || entity1Type.equals(EntityType.ZOMBIE)) {

                for (Entity entity2 : this.entities) {
                    EntityType entity2Type = entity2.getType();

                    if (upCollision(entity1, entity2)) {

                    } else if (downCollision(entity1, entity2)) {

                    } else if (leftCollision(entity1, entity2)) {

                    } else if (rightCollision(entity1, entity2)) {

                    }
                }
            }
        }
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

    private Map<Entity, Integer> initCanMoveMap(Entity e, int key) {
        Map<Entity, Integer> map = new HashMap();
        map.put(e, key);
        return map;
    }

    @Override
    public Map<Entity, Integer> canMoveUp(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.entities) {
            float[] entity2Rect = this.getEntityRect(e);

            if (entity1Rect[3] + entity1Rect[1] > entity2Rect[1]) {
                return this.initCanMoveMap(e, GameKeys.UP);
            }
        }
        return null;
    }

    @Override
    public Map<Entity, Integer> canMoveDown(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.entities) {
            float[] entity2Rect = this.getEntityRect(e);

            if (entity1Rect[1] < entity2Rect[1] + entity2Rect[3]) {
                return this.initCanMoveMap(e, GameKeys.DOWN);
            }
        }
        return null;
    }

    @Override
    public Map<Entity, Integer> canMoveLeft(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.entities) {
            float[] entity2Rect = this.getEntityRect(e);
            if (entity1Rect[0] < entity1Rect[0] + entity2Rect[2]) {
                return this.initCanMoveMap(e, GameKeys.LEFT);
            }
        }
        return null;
    }

    @Override
    public Map<Entity, Integer> canMoveRight(Entity entity) {
        float[] entity1Rect = this.getEntityRect(entity);

        for (Entity e : this.entities) {
            float[] entity2Rect = this.getEntityRect(e);

            if (entity1Rect[0] + entity1Rect[2] > entity2Rect[0]) {
                return this.initCanMoveMap(e, GameKeys.RIGHT);
            }
        }
        return null;
    }

}
