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
import group8.common.services.CollisionRequestServiceImpl;
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

    private CollisionRequestServiceImpl col = CollisionRequestServiceImpl.getInstance();
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;
    private Lookup lookup = Lookup.getDefault();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            TimerPart timerPart = player.getPart(TimerPart.class);

            boolean andUp = false, andDown = false;

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                player.setImagePath(sp.UP);
                movingPart.setUp(true);
                if (movingPart.setUp(this.col.canMoveUp(player, world))) {
                    andUp = true;
                }
            }

            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setImagePath(sp.DOWN);
                movingPart.setDown(true);

                if (movingPart.setDown(this.col.canMoveDown(player, world))) {
                    andDown = true;
                }
            }

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setImagePath(sp.LEFT);
                movingPart.setLeft(true);
                if (andUp) {
                    player.setImagePath(sp.UPLEFT);
                    movingPart.setUp(true);
                } else if (andDown) {
                    player.setImagePath(sp.DOWNLEFT);
                    movingPart.setDown(true);
                }
                movingPart.setLeft(this.col.canMoveLeft(player, world));
            }

            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setImagePath(sp.RIGHT);
                movingPart.setRight(true);
                if (andUp) {
                    player.setImagePath(sp.UPRIGHT);
                } else if (andDown) {
                    player.setImagePath(sp.DOWNRIGHT);
                }
                movingPart.setRight(this.col.canMoveRight(player, world));
            }

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                lookup.lookup(IShootService.class).shoot(player, world);
            }

            if (gameData.getKeys().isDown(GameKeys.SHIFT)) {              
                lookup.lookup(IWeaponService.class).changeWeapon();
            }
            lookup.lookup(IWeaponService.class).setWeaponDirection(player, world);

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            timerPart.process(gameData, player);

            updateShape(player);

            movingPart.setUp(false);
            movingPart.setDown(false);
            movingPart.setLeft(false);
            movingPart.setRight(false);
        }
    }

    private void colide(Entity player, int direction) {

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
