/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.enemy;
import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import group8.common.services.IEntityProcessingService;
import group8.common.data.GameKeys;
import group8.common.services.IGamePluginService;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {@ServiceProvider(service = IEntityProcessingService.class)})

/**
 *
 * @author matiasmarek
 */
public class EnemyControlSystem implements IEntityProcessingService {
     private int updateTime = 0;
    // int i = 10;
    
    
    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            
            Random random = new Random();
            int i = random.nextInt(2);
            
            switch(i){
                //make enemy go UP
                case 0: 
                    movingPart.setUp(true);
                    enemy.setImagePath(SpritePath.UP);
                    System.out.println("UP");
                    break;
                //make enemy go DOWN
                case 1: 
                    movingPart.setDown(true);
                    enemy.setImagePath(SpritePath.DOWN);
                    System.out.println("Down");
                    break;
                //make enemy go left.
                case 2: 
                    movingPart.setLeft(true);
                    enemy.setImagePath(SpritePath.LEFT);
                    break;
                case 3:
                    movingPart.setRight(true);
                    enemy.setImagePath(SpritePath.RIGHT);
                    break;
                case 4:
                    movingPart.setRight(true);
                    movingPart.setUp(true); 
                    enemy.setImagePath(SpritePath.UPRIGHT);
                    break;
                case 5:
                    movingPart.setRight(true);
                    movingPart.setDown(true); 
                    enemy.setImagePath(SpritePath.DOWNRIGHT);
                    break;
                case 6:
                    movingPart.setLeft(true);
                    movingPart.setDown(true); 
                    enemy.setImagePath(SpritePath.UPLEFT);
                    break;
                case 7:
                    movingPart.setLeft(true);
                    movingPart.setUp(true); 
                    enemy.setImagePath(SpritePath.UPRIGHT);
                    break;
                default:
                    System.out.println("Something is horribly wrong");
            }
                    
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            

            updateShape(enemy);
        }
    }
    

    
    

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        //here we draw a rectangle for the enemy
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 60);

        shapex[2] = (float) (x + 20);
        shapey[2] = (float) (y + 60);

        shapex[3] = (float) (x + 20);
        shapey[3] = (float) (y);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    

    

}


