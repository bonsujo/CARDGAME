/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jojo3
 */
public class GoFishTest {

   

    public GoFishTest() {
    }

    @Before
    public void setUp() {
    }
    
    
    //Bad test
    @Test(expected = Exception.class)
    public void testDrawBad() {
        Card expResult = GoFish.draw().EIGHT;
        Card result = GoFish.draw().EIGHT;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of draw method, of class GoFish.
     */
    @Test
    public void testDraw() {
        Card expResult = GoFish.draw().EIGHT;
        Card result = GoFish.draw().EIGHT;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
