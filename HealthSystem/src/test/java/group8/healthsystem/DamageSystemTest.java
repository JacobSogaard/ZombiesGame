/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.healthsystem;

import group8.common.data.Entity;
import group8.common.data.World;
import group8.common.data.entityparts.DamagePart;
import group8.common.data.entityparts.LifePart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacob
 */
public class DamageSystemTest {

    public DamageSystemTest() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of collisionDetected method, of class DamageSystem.
     */
    @Test
    public void testCollisionDetected() throws InterruptedException {
        System.out.println("collisionDetected");
        Entity entity1 = new Entity();
        Entity entity2 = new Entity();
        World world = new World();
        DamageSystem instance = new DamageSystem();
        
        
        //Same life and damage
        System.out.println("Same life and damage test....");
        entity1.add(damage(5));
        entity1.add(life(5));
        entity2.add(damage(5));
        entity2.add(life(5));
        world.addEntity(entity1);
        world.addEntity(entity2);
        instance.collisionDetected(entity1, entity2, world);
        assertEquals("Same life and damage", 0, world.getEntities().size());
        
        this.removeAllEntities(world);
        
        
        //One entity with higher damage
        System.out.println("One entity with higher damage test....");
        entity1.add(damage(5));
        entity1.add(life(5));
        entity2.add(damage(7));
        entity2.add(life(5));
        world.addEntity(entity1);
        world.addEntity(entity2);
        instance.collisionDetected(entity1, entity2, world);
        assertEquals("Same life and damage", 0, world.getEntities().size());
        
        this.removeAllEntities(world);
        
        
        //One entity with higher life
        System.out.println("One entity with higher life test....");
        entity1.add(damage(5));
        entity1.add(life(5));
        entity2.add(damage(5));
        entity2.add(life(10));
        world.addEntity(entity1);
        world.addEntity(entity2);
        instance.collisionDetected(entity1, entity2, world);
        assertEquals("Same life and damage", 1, world.getEntities().size());
        
        //Test life of one entity after losing health
        System.out.println("Life lost test...");
        LifePart lifePart = entity2.getPart(LifePart.class);
        assertEquals("New Health", 5, lifePart.getLife());
        
        this.removeAllEntities(world);
        
        
        //Zero life entity
        System.out.println("One entity with zero life test....");
        entity1.add(damage(1));
        entity1.add(life(0));
        entity2.add(damage(5));
        entity2.add(life(10));
        world.addEntity(entity1);
        world.addEntity(entity2);
        instance.collisionDetected(entity1, entity2, world);
        assertEquals("Same life and damage", 1, world.getEntities().size());
        
        this.removeAllEntities(world);
        
        //Both more life than damage
        System.out.println("Both more life than damage test....");
        entity1.add(damage(5));
        entity1.add(life(20));
        entity2.add(damage(5));
        entity2.add(life(20));
        world.addEntity(entity1);
        world.addEntity(entity2);
        instance.collisionDetected(entity1, entity2, world);
        assertEquals("Same life and damage", 2, world.getEntities().size());
    }
    
    private void removeAllEntities(World world) throws InterruptedException{
        //Thread.sleep(5000);
        for (Entity e : world.getEntities()) {
            world.removeEntity(e);
        }
    }

    private DamagePart damage(int damage) {
        DamagePart damagePart = new DamagePart(damage);
        return damagePart;
    }
    
    private LifePart life(int life) {
        LifePart lifePart = new LifePart(life);
        return lifePart;
    }

}
