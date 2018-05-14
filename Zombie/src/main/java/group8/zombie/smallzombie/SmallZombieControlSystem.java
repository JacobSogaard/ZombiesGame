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
import group8.common.services.IMoveCollisionService;
import group8.commonenemy.services.IPathFinderService;
import group8.zombie.smallzombie.SmallZombieSpritePath;
import group8.zombie.Zombie;
import java.util.ArrayList;
import java.util.List;
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
public class SmallZombieControlSystem implements IEntityProcessingService {

    private Lookup lookup = Lookup.getDefault();
    protected IPathFinderService path = lookup.lookup(IPathFinderService.class);
    private int dirIndex = 0;
    private List<Integer> directions;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity zombie : world.getEntities(SmallZombie.class)) {
            //Map<Integer, Boolean> directions = path.getDirections(zombie);
            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            while (this.directions == null || this.directions.size() == this.dirIndex) {
                this.getDirections(zombie);
            }

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

            if (directions.get(dirIndex) == GameKeys.UP) {
                zombie.setImagePath(SmallZombieSpritePath.UP);
                if (!upCol) {
                    movingPart.setUp(true);
                }
                andUp = true;

            }

            if (directions.get(dirIndex) == GameKeys.DOWN) {
                zombie.setImagePath(SmallZombieSpritePath.DOWN);
                if (!downCol) {
                    movingPart.setDown(true);
                }
                andDown = true;

            }

            if (directions.get(dirIndex) == GameKeys.LEFT) {
                zombie.setImagePath(SmallZombieSpritePath.LEFT);
                if (andUp) {
                    zombie.setImagePath(SmallZombieSpritePath.UPLEFT);
                } else if (andDown) {
                    zombie.setImagePath(SmallZombieSpritePath.DOWNLEFT);
                }
                if (!leftCol) {
                    movingPart.setLeft(true);
                }
            }

            if (directions.get(dirIndex) == GameKeys.RIGHT) {
                zombie.setImagePath(SmallZombieSpritePath.RIGHT);

                if (andUp) {
                    zombie.setImagePath(SmallZombieSpritePath.UPRIGHT);
                } else if (andDown) {
                    zombie.setImagePath(SmallZombieSpritePath.DOWNRIGHT);
                }
                if (!rightCol) {
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
            this.dirIndex++;
        }
    }

    private void getDirections(Entity zombie) {
        try {
            this.directions = path.AStarDirections(zombie);
        } catch (NullPointerException ex) {
            this.directions = new ArrayList<>();
            Random r = new Random();
            directions.add(r.nextInt(4));
        }
        this.dirIndex = 0;
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
        shapey[1] = (float) (y + 50);

        shapex[2] = (float) (x + 28);
        shapey[2] = (float) (y + 50);

        shapex[3] = (float) (x + 28);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
