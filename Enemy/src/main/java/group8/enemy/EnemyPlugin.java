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
import group8.common.services.IGamePluginService;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;


@ServiceProviders(value = {@ServiceProvider(service = IGamePluginService.class)})
/**
 *
 * @author matiasmarek
 */
public class EnemyPlugin implements IGamePluginService {
    
    private Entity enemy; 

    @Override
    public void start(GameData gameData, World world) {
        //Add entitites to world
        enemy = createEnemy(gameData);
        world.addEntity(enemy); 
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
        
    }
    
    public Entity createEnemy(GameData gameData) {
        float speed = 3;         
        float acceleration = 300;
        float rotationSpeed = 50;
        float decelaration = 200;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayWidth() / 2;  
        float radians = 3.1415f / 2;
        
        Entity enemyRectangle =  new Enemy();
        enemyRectangle.add(new MovingPart(speed, acceleration, rotationSpeed, decelaration));
        enemyRectangle.add(new PositionPart(x, y, radians));
        
        enemyRectangle.setImagePath(SpritePath.UP);
        
        return enemyRectangle; 
    }
    
}
