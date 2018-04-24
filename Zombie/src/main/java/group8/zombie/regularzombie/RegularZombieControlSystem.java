/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.regularzombie;

import group8.zombie.bigzombie.*;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.GameKeys;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.commonenemy.services.IPathFinderService;
import group8.zombie.smallzombie.SmallZombieSpritePath;
import group8.zombie.Zombie;
import java.util.Map;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


/**
 *
 * @author jacob
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})
public class RegularZombieControlSystem implements IEntityProcessingService {

    private Lookup lookup = Lookup.getDefault();
    protected IPathFinderService path = lookup.lookup(IPathFinderService.class);
    
    @Override
    public void process(GameData gameData, World world) {
        
        
        for (Entity zombie : world.getEntities(RegularZombie.class)) {
            Map<Integer, Boolean> directions = path.getDirections(zombie);
            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;
            
            
            if (directions.get(GameKeys.UP)) {
                zombie.setImagePath(RegularZombieSpritePath.UP);
                movingPart.setUp(true);
                andUp = true;
                
            }

            if (directions.get(GameKeys.DOWN)) {
                zombie.setImagePath(RegularZombieSpritePath.DOWN);

                movingPart.setDown(true);
                andDown = true;
                
            }

            if (directions.get(GameKeys.LEFT)) {
                zombie.setImagePath(RegularZombieSpritePath.LEFT);
                if (andUp) {
                    zombie.setImagePath(RegularZombieSpritePath.UPLEFT);
                } else if (andDown) {
                    zombie.setImagePath(RegularZombieSpritePath.DOWNLEFT);
                }
                movingPart.setLeft(true);
            }

            if (directions.get(GameKeys.RIGHT)) {
                zombie.setImagePath(RegularZombieSpritePath.RIGHT);

                if (andUp) {
                    zombie.setImagePath(RegularZombieSpritePath.UPRIGHT);
                } else if (andDown) {
                    zombie.setImagePath(RegularZombieSpritePath.DOWNRIGHT);
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