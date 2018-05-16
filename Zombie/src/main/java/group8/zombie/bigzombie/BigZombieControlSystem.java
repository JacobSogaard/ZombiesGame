package group8.zombie.bigzombie;

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
 * Control system for BigZombie. Handles the zombie movement and implements the
 * IEntityProcessesing service
 * @author group 8
 */
@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})
public class BigZombieControlSystem implements IEntityProcessingService {

    private Lookup lookup = Lookup.getDefault();
    protected IPathFinderService path = lookup.lookup(IPathFinderService.class);
    private Random r;
    private Map<Integer, Boolean> directions = new HashMap<>();

    @Override
    public void process(GameData gameData, World world) {

        //Iterate through all big zombies in world
        for (Entity zombie : world.getEntities(BigZombie.class)) {
            try {
                this.directions = path.getDirections(zombie);
            } catch (NullPointerException ex) {
                this.directions.put(0, false);
                this.directions.put(1, false);
                this.directions.put(2, false);
                this.directions.put(3, false);
                this.r = new Random();
                int dirIndex = r.nextInt(4);
                this.directions.put(dirIndex, true);
            }

            PositionPart positionPart = zombie.getPart(PositionPart.class);
            MovingPart movingPart = zombie.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;
            boolean upCol, downCol, leftCol, rightCol;

            //Call to collision service if there is one, else set all direction col booleans to false
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

            //UP
            if (directions.get(GameKeys.UP)) {
                zombie.setImagePath(BigZombieSpritePath.UP);
                if (!upCol) {
                    movingPart.setUp(true);
                }
                andUp = true;
            }
            //DOWN
            if (directions.get(GameKeys.DOWN)) {
                zombie.setImagePath(BigZombieSpritePath.DOWN);

                if (!downCol) {
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
                if (!leftCol) {
                    movingPart.setLeft(true);
                }
            }

            //Right
            if (directions.get(GameKeys.RIGHT)) {
                zombie.setImagePath(BigZombieSpritePath.RIGHT);

                if (andUp) {
                    zombie.setImagePath(BigZombieSpritePath.UPRIGHT);
                } else if (andDown) {
                    zombie.setImagePath(BigZombieSpritePath.DOWNRIGHT);
                }
                if (!rightCol) {
                    movingPart.setRight(true);
                }
            }
            movingPart.process(gameData, zombie);
            positionPart.process(gameData, zombie);

            updateShape(zombie);

            //Reset all movingparts
            movingPart.setUp(false);
            movingPart.setDown(false);
            movingPart.setLeft(false);
            movingPart.setRight(false);
        }
    }

    //Method to update shape of entity, is just the collision box
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
