/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.player;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.data.GameKeys;
import group8.common.data.entityparts.TimerPart;
import group8.common.services.IMoveCollisionService;
import group8.common.services.IShootService;
import group8.common.services.IWeaponService;
import java.util.Arrays;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)})

/**
 *
 * @author matiasmarek
 */
public class PlayerControlSystem implements IEntityProcessingService {

    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private Lookup lookup = Lookup.getDefault();
    private int direction;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            TimerPart timerPart = player.getPart(TimerPart.class);

            boolean andUp = false, andDown = false;
            boolean upCol, downCol, leftCol, rightCol;

            try {
                IMoveCollisionService colService = lookup.lookup(IMoveCollisionService.class);
                upCol = colService.checkUpCollision(player, world);
                downCol = colService.checkDownCollision(player, world);
                leftCol = colService.checkLeftCollision(player, world);
                rightCol = colService.checkRightCollision(player, world);
            } catch (NullPointerException ex) {
                upCol = false;
                downCol = false;
                leftCol = false;
                rightCol = false;
            }

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                positionPart.setRadians(0);
                player.setImagePath(sp.UP);
                direction = 0;
                if (!upCol) {
                    movingPart.setUp(true);
                }

                andUp = true;
            }

            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setImagePath(sp.DOWN);
                direction = 1;
                if (!downCol) {
                    movingPart.setDown(true);
                }

                andDown = true;
            }

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setImagePath(sp.LEFT);
                direction = 2;
                if (!leftCol) {
                    movingPart.setLeft(true);
                }

                if (andUp) {
                    direction = 3;
                    player.setImagePath(sp.UPLEFT);
                    if (!upCol) {
                        movingPart.setUp(true);
                    }

                } else if (andDown) {
                    direction = 4;
                    player.setImagePath(sp.DOWNLEFT);
                    if (!downCol) {
                        movingPart.setDown(true);
                    }
                }
            }

            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                direction = 5;
                player.setImagePath(sp.RIGHT);
                if (!rightCol) {
                    movingPart.setRight(true);
                }

                if (andUp) {
                    direction = 6;
                    player.setImagePath(sp.UPRIGHT);
                } else if (andDown) {
                    direction = 7;
                    player.setImagePath(sp.DOWNRIGHT);
                }
            }

            movingPart.setDirection(direction);

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                try {
                    lookup.lookup(IShootService.class).shoot(player, world);
                } catch (NullPointerException e) {
                    System.out.println("Shoot");
                }

            }
//
//            if (gameData.getKeys().isDown(GameKeys.SHIFT)) {
//                lookup.lookup(IWeaponService.class).changeWeapon();
//            }
//            lookup.lookup(IWeaponService.class).setWeaponDirection(player, world);

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            timerPart.process(gameData, player);
            //Detect collision when player walks into an entity. 

            updateShape(player);

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

        //here we draw a rectangle for the player
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
