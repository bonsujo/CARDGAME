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
public class CardTest {

    public CardTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of values method, of class Card.
     */
    @Test
    //Bad Test
    public void testValues() {
        Card expResult = Card.ACE;
        Card result = Card.ACE;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test(expected = Exception.class)
    //Bad Test
    public void testValuesBAD() {
        Card expResult = Card.EIGHT;
        Card result = Card.ACE;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Card.
     */
    //Good Test
    @Test
    public void testValueOf() {
        String name = "ACE";
        Card expResult = Card.ACE;
        Card result = Card.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");

    }

    //Bad Test
    @Test(expected = Exception.class)
    public void testValueOfBad() {
        String name = "KING";
        Card expResult = Card.ACE;
        Card result = Card.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
