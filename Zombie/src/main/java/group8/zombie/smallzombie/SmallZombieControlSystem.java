/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.smallzombie;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.GameKeys;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.zombie.smallzombie.SpritePath;
import group8.zombie.Zombie;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})
public class SmallZombieControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity zombie : world.getEntities(Zombie.class)) {

            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                zombie.setImagePath(SpritePath.UP);
                movingPart.setUp(true);
                andUp = true;
                
            }

            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                zombie.setImagePath(SpritePath.DOWN);

                movingPart.setDown(true);
                andDown = true;
                
            }

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                zombie.setImagePath(SpritePath.LEFT);
                if (andUp) {
                    zombie.setImagePath(SpritePath.UPLEFT);
                } else if (andDown) {
                    zombie.setImagePath(SpritePath.DOWNLEFT);
                }
                movingPart.setLeft(true);
            }

            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                zombie.setImagePath(SpritePath.RIGHT);

                if (andUp) {
                    zombie.setImagePath(SpritePath.UPRIGHT);
                } else if (andDown) {
                    zombie.setImagePath(SpritePath.DOWNRIGHT);
                }
                movingPart.setRight(true);
            }

            movingPart.process(gameData, zombie);
            positionPart.process(gameData, zombie);

            updateShape(zombie);

            movingPart.setUp(false);
            movingPart.setDown(false);
            movingPart.setLeft(false);
            movingPart.setRight(false);
        }
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        //float radians = positionPart.getRadians();

        //here we draw a rectangle for the zombie
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 70);

        shapex[2] = (float) (x + 40);
        shapey[2] = (float) (y + 70);

        shapex[3] = (float) (x + 40);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
}
