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
    private Entity colEntity;
    private boolean canMoveUp = true;
    private boolean canMoveDown = true;
    private boolean canMoveLeft = true;
    private boolean canMoveRight = true;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {

            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);

            boolean andUp = false, andDown = false;

            this.colEntity = col.collisionRequest(player, world);
            
            if (gameData.getKeys().isDown(GameKeys.UP)){
                this.canMoveUp = this.colEntity.getType() == EntityType.NONE;
                
                if(this.canMoveUp){
                    player.setImagePath(SpritePath.UP);
                    andUp = true;
                    //this.canMoveUp = true;
                    movingPart.setUp(this.canMoveUp);
                } else {
                    System.out.println("Up: " + canMoveUp + "  " + colEntity.getType());
                    //this.canMoveUp = false;
                    movingPart.setUp(this.canMoveUp);
                    this.colide(this.colEntity, player, GameKeys.UP);
                }
            }
            
//            //Up movement
//            if (movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP)
//                    && this.canMoveUp)) {
//                player.setImagePath(SpritePath.UP);
//                andUp = true;
//                this.canMoveDown = true;
//                this.canMoveUp = this.colEntity.getType() == EntityType.NONE;
//            } else {
//                this.colide(this.colEntity, player, GameKeys.UP);
//            }

            if (gameData.getKeys().isDown(GameKeys.DOWN)){
                
                if(this.canMoveDown || this.colEntity.getType() == EntityType.NONE){
                    player.setImagePath(SpritePath.DOWN);
                    andUp = true;
                    this.canMoveDown = true;
                    movingPart.setDown(this.canMoveDown);
                } else {
                    System.out.println("Down: " + canMoveDown + "  " + colEntity.getType());
                    this.canMoveDown = false;
                    movingPart.setDown(this.canMoveDown);
                    this.colide(this.colEntity, player, GameKeys.DOWN);
                }
            }

            //Down movement
//            if (movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN)
//                    && this.canMoveDown)) {
//                player.setImagePath(SpritePath.DOWN);
//                andDown = true;
//                this.canMoveUp = true;
//                this.canMoveDown = this.colEntity.getType() == EntityType.NONE;
//            } else {
//                this.colide(this.colEntity, player, GameKeys.DOWN);
//
//            }

            //Left movement
            if (movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT))) {
                //this.colEntity = col.collisionRequest(player, GameKeys.LEFT);
                if (this.colEntity.getType() == EntityType.NONE) {
                    if (andUp) {
                        player.setImagePath(SpritePath.UPLEFT);
                    } else if (andDown) {
                        player.setImagePath(SpritePath.DOWNLEFT);
                    } else {
                        player.setImagePath(SpritePath.LEFT);
                    }
                } else {
                    this.colide(this.colEntity, player, GameKeys.LEFT);
                }
            }

            //Right movement
            if (movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT))) {
                //this.colEntity = col.collisionRequest(player, GameKeys.RIGHT);
                if (this.colEntity.getType() == EntityType.NONE) {
                    if (andUp) {
                        player.setImagePath(SpritePath.UPRIGHT);
                    } else if (andDown) {
                        player.setImagePath(SpritePath.DOWNRIGHT);
                    } else {
                        player.setImagePath(SpritePath.RIGHT);
                    }
                } else {
                    this.colide(this.colEntity, player, GameKeys.RIGHT);
                }
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

            updateShape(player);
            
            movingPart.setUp(false);
            movingPart.setDown(false);
        }
    }

    private void colide(Entity e, Entity player, int direction) {
        
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);

        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        //here we draw a rectangle for the player
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 30);

        shapex[2] = (float) (x + 20);
        shapey[2] = (float) (y + 30);

        shapex[3] = (float) (x + 20);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
