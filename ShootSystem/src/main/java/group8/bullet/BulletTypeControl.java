/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.bullet;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IEntityProcessingService;
import java.util.Arrays;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})

/**
 *
 * @author MER
 */
public class BulletTypeControl implements IEntityProcessingService {

    
    @Override
    public void process(GameData gameData, World world) {
        
        for (Entity entity : world.getEntities(Bullet.class)) {
            PositionPart part1 = entity.getPart(PositionPart.class);
            MovingPart moving = entity.getPart(MovingPart.class);
            TimerPart timer = entity.getPart(TimerPart.class);

            part1.process(gameData, entity);
            moving.process(gameData, entity);
            timer.process(gameData, entity);

            bulletTimer(timer, world, entity);

            this.updateShape(entity);

        }
    }


    private void bulletTimer(TimerPart timer, World world, Entity entity) {

        if (timer.getExpiration() == 0) {
            world.removeEntity(entity);
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
        shapey[1] = (float) (y + entity.getHeight());

        shapex[2] = (float) (x + entity.getWidth());
        shapey[2] = (float) (y + entity.getHeight());

        shapex[3] = (float) (x + entity.getWidth());
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
