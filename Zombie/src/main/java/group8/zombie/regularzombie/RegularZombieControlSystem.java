/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.zombie.regularzombie;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.GameKeys;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.services.IMoveCollisionService;
import group8.commonenemy.services.IPathFinderService;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
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
    private Random r;
    private Map<Integer, Boolean> directions = new HashMap<>();
    
    @Override
    public void process(GameData gameData, World world) {
        
        
        for (Entity zombie : world.getEntities(RegularZombie.class)) {
            try {
            this.directions = path.getDirections(zombie);
            } catch (NullPointerException ex) {
                this.directions.put(0, false);
                this.directions.put(1, false);
                this.directions.put(2, false);
                this.directions.put(3, false);
                this.r = new Random();
                int dirIndex = r.nextInt(3) + 1;
                this.directions.put(dirIndex, true);
            }
            
            
            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;
            boolean upCol, downCol, leftCol, rightCol;

            try {
                IMoveCollisionService colService = lookup.lookup(IMoveCollisionService.class);
                upCol = colService.checkUpCollision(zombie, world);
                downCol = colService.checkDownCollision(zombie, world);
                leftCol = colService.checkLeftCollision(zombie, world);
                rightCol = colService.checkRightCollision(zombie, world);
            } catch (NullPointerException ex) {
                upCol = false;
                downCol = false;
                leftCol = false;
                rightCol = false;
            }
            
            if (directions.get(GameKeys.UP)) {
                zombie.setImagePath(RegularZombieSpritePath.UP);
                if(!upCol){
                    movingPart.setUp(true);
                }
                andUp = true;
                
            }

            if (directions.get(GameKeys.DOWN)) {
                zombie.setImagePath(RegularZombieSpritePath.DOWN);
                if(!downCol){
                    movingPart.setDown(true);
                }
                andDown = true;
                
            }

            if (directions.get(GameKeys.LEFT)) {
                zombie.setImagePath(RegularZombieSpritePath.LEFT);
                if (andUp) {
                    zombie.setImagePath(RegularZombieSpritePath.UPLEFT);
                } else if (andDown) {
                    zombie.setImagePath(RegularZombieSpritePath.DOWNLEFT);
                }
                if(!leftCol){
                movingPart.setLeft(true);
                }
            }

            if (directions.get(GameKeys.RIGHT)) {
                zombie.setImagePath(RegularZombieSpritePath.RIGHT);

                if (andUp) {
                    zombie.setImagePath(RegularZombieSpritePath.UPRIGHT);
                } else if (andDown) {
                    zombie.setImagePath(RegularZombieSpritePath.DOWNRIGHT);
                }
                if(!rightCol){
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
        shapey[1] = (float) (y + 70);

        shapex[2] = (float) (x + 40);
        shapey[2] = (float) (y + 70);

        shapex[3] = (float) (x + 40);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
}
