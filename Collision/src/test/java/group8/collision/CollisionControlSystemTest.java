/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.collision;

import group8.common.data.Entity;
import group8.common.data.World;
import group8.common.data.entityparts.MovingPart;
import group8.common.data.entityparts.PositionPart;
import org.junit.After;
import org.junit.Before;
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
        
        //To test - remove comment from the setMockEntity call for the test that should be run
        //and remove comment from the corresponding test. 
        //Same entity
        //this.setMockEntity(x, y);
        
        //Up edge case
        //this.setMockEntity(x, y - 71);
        
       //down edge case
       //this.setMockEntity(x, y + 71);
       
       //left edge case
       //this.setMockEntity(x - 41, y);
       
       //right edge case
       //this.setMockEntity(x + 41, y);
       
       //No collision
       //this.setMockEntity(x + 400, y + 400);
        
        
    }
    
    @After
    public void tearDown() {
    }
    
    private void setMockEntity(float newx, float newy){
        move = new MovingPart(2); //Sets the speed, is just used in collision
        
        float x = newx;
        float y = newy;
        
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
        CollisionControlSystem instance = new CollisionControlSystem();
        boolean expResult = true;
        boolean result = instance.detectCollision(mockEntity, mockWorld);
        assertEquals("Collision test", expResult, result);
    }

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

//    /**
//     * Test of checkRightCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckRightCollision() {
//        System.out.println("checkRightCollision");
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = true;
//        boolean result = instance.checkRightCollision(this.mockEntity, this.mockWorld);
//        // TODO review the generated test code and remove the default call to fail.
//        assertEquals(result, expResult);
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
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = true;
//        boolean result = instance.checkLeftCollision(this.mockEntity, this.mockWorld);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of checkUpCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckUpCollision() {
//        System.out.println("checkUpCollision");
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = true;
//        boolean result = instance.checkUpCollision(this.mockEntity, this.mockWorld);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of checkDownCollision method, of class CollisionControlSystem.
//     */
//    @org.junit.Test
//    public void testCheckDownCollision() {
//        System.out.println("checkDownCollision");
//        CollisionControlSystem instance = new CollisionControlSystem();
//        boolean expResult = true;
//        boolean result = instance.checkDownCollision(this.mockEntity, this.mockWorld);
//        assertEquals(expResult, result);
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
