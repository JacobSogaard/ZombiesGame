/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.shootsystem;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;

/**
 *
 * @author MER
 */
public class BulletTypeControl implements IEntityProcessingService {
    
    
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Bullet.class)) {
            this.updateShape(entity);
            PositionPart part1 = entity.getPart(Bullet.class);
            MovingPart part = entity.getPart(Bullet.class);
                part.setLeft(false);
                part.setDown(false);
                part.setRight(false);
                part.setUp(true);
        }
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        //float radians = positionPart.getRadians();

        //here we draw a rectangle for the player
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 20);

        shapex[2] = (float) (x + 20);
        shapey[2] = (float) (y + 20);

        shapex[3] = (float) (x + 20);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    
    
}
