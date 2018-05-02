/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.player;

import group8.common.data.Entity;
import group8.common.data.EntityType;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.data.GameKeys;
import group8.common.services.CollisionRequestServiceImpl;
import group8.common.services.ICollisionRequestService;
import group8.common.services.IGamePluginService;
import group8.player.SpritePath2;
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

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;

            if (gameData.getKeys().isDown(GameKeys.UP)) {
                player.setImagePath(SpritePath.UP);

                if (movingPart.setUp(this.col.canMoveUp(player, world))) {
                    andUp = true;
                }
            }

            if (gameData.getKeys().isDown(GameKeys.DOWN)) {
                player.setImagePath(SpritePath.DOWN);

                if (movingPart.setDown(this.col.canMoveDown(player, world))) {
                    andDown = true;
                }
            }

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setImagePath(SpritePath.LEFT);
                if (andUp) {
                    player.setImagePath(SpritePath.UPLEFT);
                } else if (andDown) {
                    player.setImagePath(SpritePath.DOWNLEFT);
                }
                movingPart.setLeft(this.col.canMoveLeft(player, world));
            }

            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setImagePath(SpritePath.RIGHT);

                if (andUp) {
                    player.setImagePath(SpritePath.UPRIGHT);
                } else if (andDown) {
                    player.setImagePath(SpritePath.DOWNRIGHT);
                }
                movingPart.setRight(this.col.canMoveRight(player, world));
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

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
