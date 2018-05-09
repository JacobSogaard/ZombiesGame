/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.bigzombie;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.GameKeys;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IGamePluginService;
import group8.common.services.IMoveCollisionService; 
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
public class BigZombieControlSystem implements IEntityProcessingService {

    private Lookup lookup = Lookup.getDefault();
    protected IPathFinderService path = lookup.lookup(IPathFinderService.class);
    
    @Override
    public void process(GameData gameData, World world) {
        
        
        for (Entity zombie : world.getEntities(BigZombie.class)) {
            Map<Integer, Boolean> directions = path.getDirections(zombie);
            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;
            
            //UP
            if (directions.get(GameKeys.UP)) {
                zombie.setImagePath(BigZombieSpritePath.UP);
                if(!lookup.lookup(IMoveCollisionService.class).checkUpCollision(zombie, world)){
                    movingPart.setUp(true);
                }
                andUp = true;
            }
            //DOWN
            if (directions.get(GameKeys.DOWN)) {
                zombie.setImagePath(BigZombieSpritePath.DOWN);
                
                if(!lookup.lookup(IMoveCollisionService.class).checkDownCollision(zombie, world)){
                    movingPart.setDown(true);
                }
                andDown = true;
            }
            //Left
            if (directions.get(GameKeys.LEFT)) {
                zombie.setImagePath(BigZombieSpritePath.LEFT);
                if (andUp) {
                    zombie.setImagePath(BigZombieSpritePath.UPLEFT);
                } else if (andDown) {
                    zombie.setImagePath(BigZombieSpritePath.DOWNLEFT);
                }
                if(!lookup.lookup(IMoveCollisionService.class).checkLeftCollision(zombie, world)){
                movingPart.setLeft(true);
                }
            }

            if (directions.get(GameKeys.RIGHT)) {
                zombie.setImagePath(BigZombieSpritePath.RIGHT);

                if (andUp) {
                    zombie.setImagePath(BigZombieSpritePath.UPRIGHT);
                } else if (andDown) {
                    zombie.setImagePath(BigZombieSpritePath.DOWNRIGHT);
                }
                 if(!lookup.lookup(IMoveCollisionService.class).checkRightCollision(zombie, world)){
                movingPart.setRight(true);
                 }
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
        shapey[1] = (float) (y + 100);

        shapex[2] = (float) (x + 58);
        shapey[2] = (float) (y + 100);

        shapex[3] = (float) (x + 58);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
}
