/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group8.ai;

import group8.common.data.Entity;
import java.util.List;
import java.util.Map;
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
public class EnemyMovementTest {
    
    public EnemyMovementTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDirections method, of class EnemyMovement.
     */
    @org.junit.Test
    public void testGetDirections() {
        System.out.println("getDirections");
        Entity enemy = null;
        EnemyMovement instance = new EnemyMovement();
        Map<Integer, Boolean> expResult = null;
        Map<Integer, Boolean> result = instance.getDirections(enemy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AStarDirections method, of class EnemyMovement.
     */
    @org.junit.Test
    public void testAStarDirections() {
        System.out.println("AStarDirections");
        Entity enemy = null;
        EnemyMovement instance = new EnemyMovement();
        List<Integer> expResult = null;
        List<Integer> result = instance.AStarDirections(enemy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
