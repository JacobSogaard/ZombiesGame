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
import group8.common.services.IGamePluginService;
import group8.player.SpritePath2;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {@ServiceProvider(service = IEntityProcessingService.class)})

/**
 *
 * @author matiasmarek
 */
public class PlayerControlSystem implements IEntityProcessingService {
    
    
    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            
            boolean andUp = false, andDown = false;
            
            
            if (movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP))) {
                player.setImagePath(SpritePath.UP);
                andUp = true;
            }
            
            if (movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN))) {
                player.setImagePath(SpritePath.DOWN);
                andDown = true;
            }
            
            if (movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT))){
                if (andUp)
                player.setImagePath(SpritePath.UPLEFT);
                else if (andDown)
                    player.setImagePath(SpritePath.DOWNLEFT);
                else
                    player.setImagePath(SpritePath.LEFT);
            }
            
            if (movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT))) {
                if (andUp)
                    player.setImagePath(SpritePath.UPRIGHT);
                else if (andDown)
                    player.setImagePath(SpritePath.DOWNRIGHT);
                else
                    player.setImagePath(SpritePath.RIGHT);
            }
            System.out.println(player.getImagePath());
            
            
            
            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            

            updateShape(player);
        }
    }
    
    

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        //here we draw a rectangle for the player
        shapex[0] = (float) (x - 10);
        shapey[0] = (float) (y - 15);

        shapex[1] = (float) (x - 10);
        shapey[1] = (float) (y + 15);

        shapex[2] = (float) (x + 10);
        shapey[2] = (float) (y + 15);

        shapex[3] = (float) (x + 10);
        shapey[3] = (float) (y - 15);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    

    

}


