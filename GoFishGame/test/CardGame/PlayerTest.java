/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jojo3
 */
public class PlayerTest {
        Card cards;

    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of hasGiven method, of class Player.
     */
    @Test
    public void testHasGiven() {
        Card cType = cards.JACK;
        Player instance = new PlayerImpl();
        boolean result = instance.hasGiven(cards.JACK);
        assertEquals(cType, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getNumBooks method, of class Player.
     */
    @Test
    public void testGetNumBooks() {
        Player instance = new PlayerImpl();
        int expResult = instance.getNumBooks();
        int result = instance.getNumBooks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }



    public class PlayerImpl extends Player {

        public void haveTurn() {
        }
    }
    
}
