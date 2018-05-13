/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.GameData;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import java.awt.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jacob
 */
public class CollisionControlSystemTest {
    private Entity worldEntity, mockEntity;
    private MovingPart move;
    private PositionPart pos;
    private World mockWorld;
    
    public CollisionControlSystemTest() {
    }
    
    @Before
    public void setUp() {
        this.mockWorld = new World();
        move = new MovingPart(2); //Sets the speed, is just used in collision
        
        float x = 300;
        float y = 300;
        
        pos = new PositionPart(x, y, 0); //Position of the worldEntity
        
        worldEntity = new Entity();
        worldEntity.add(pos);
        worldEntity.add(move);
        float[] shapex = worldEntity.getShapeX();
        float[] shapey = worldEntity.getShapeY();

        //here we draw a rectangle for the zombie
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 70);

        shapex[2] = (float) (x + 40);
        shapey[2] = (float) (y + 70);

        shapex[3] = (float) (x + 40);
        shapey[3] = (float) (y);
        
        worldEntity.setShapeX(shapex);
        worldEntity.setShapeY(shapey);
        worldEntity.setHeight(70);
        worldEntity.setWidth(40);
        
        mockWorld.addEntity(worldEntity);
        
        
    }
    
    @After
    public void tearDown() {
    }
    
    private void setMockEntity(){
        move = new MovingPart(2); //Sets the speed, is just used in collision
        
        float x = 299;
        float y = 300;
        
        pos = new PositionPart(x, y, 0); //Position of the worldEntity
        
        this.mockEntity = new Entity();
        this.mockEntity.add(pos);
        this.mockEntity.add(move);
        float[] shapex = new float[4];
        float[] shapey = new float[4];

        //here we draw a rectangle for the zombie
        shapex[0] = (float) (x);
        shapey[0] = (float) (y);

        shapex[1] = (float) (x);
        shapey[1] = (float) (y + 70);

        shapex[2] = (float) (x + 40);
        shapey[2] = (float) (y + 70);

        shapex[3] = (float) (x + 40);
        shapey[3] = (float) (y);
        
//        mockEntity.setHeight(70);
//        mockEntity.setWidth(40);
        mockEntity.setShapeX(shapex);
        mockEntity.setShapeY(shapey);
    }

    /**
     * Test of detectCollision method, of class CollisionControlSystem.
     */
    @org.junit.Test
    public void testDetectCollision() {
        System.out.println("detectCollision");
        this.setMockEntity();
        CollisionControlSystem instance = new CollisionControlSystem();
        boolean expResult = true;
        boolean result = instance.detectCollision(mockEntity, mockWorld);
        assertEquals("Collision test", expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("fail");
    }
//
//    /**
//     * Test of rectangleIntersection method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testRectangleIntersection() {
//        System.out.println("rectangleIntersection");
//        Rectangle rectangle1 = null;
//        Rectangle rectangle2 = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        Rectangle expResult = null;
//        Rectangle result = instance.rectangleIntersection(rectangle1, rectangle2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkRightCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckRightCollision() {
//        System.out.println("checkRightCollision");
//        Entity entity = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = false;
//        boolean result = instance.checkRightCollision(entity, world);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of start method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testStart() {
//        System.out.println("start");
//        GameData gameData = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        instance.start(gameData, world);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of stop method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testStop() {
//        System.out.println("stop");
//        GameData gameData = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        instance.stop(gameData, world);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkLeftCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckLeftCollision() {
//        System.out.println("checkLeftCollision");
//        Entity entity = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = false;
//        boolean result = instance.checkLeftCollision(entity, world);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkUpCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckUpCollision() {
//        System.out.println("checkUpCollision");
//        Entity entity = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = false;
//        boolean result = instance.checkUpCollision(entity, world);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkDownCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckDownCollision() {
//        System.out.println("checkDownCollision");
//        Entity entity = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = false;
//        boolean result = instance.checkDownCollision(entity, world);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of spawnHere method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testSpawnHere() {
//        System.out.println("spawnHere");
//        Entity entity = null;
//        GameData gameData = null;
//        World world = null;
//        CollisionControlSystem instance = new CollisionControlSystem();
//        Entity expResult = null;
//        Entity result = instance.spawnHere(entity, gameData, world);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
