package ua.toe.tac.tic.model;

import org.junit.Test;
import ua.toe.tac.tic.enums.Player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit-level testing for {@link TheMove} object;
 */
public class TheMoveUnitTest {

    //given: 'valid the move object'
    private int height = 10;
    private int width = 15;
    private TheMove move = new TheMove( new Point( width, height), Player.X );


    @Test
    public void testMoveTo(){
        //when: 'an expected method is called'
        move.moveTo( 5, 5 );

        //then: 'the Point object is updated as expected'
        assertEquals( move.getPoint().getY(), height + 5 );
        assertEquals( move.getPoint().getX(), width + 5 );
    }

    @Test
    public void testSetDefaultCoincidenceCount() {
        //and: 'set coincidence count non-equal default value.'
        move.incrementCoincidence();
        move.incrementCoincidence();
        move.incrementCoincidence();

        //when: 'an expected method is called'
        move.setDefaultCoincidence();

        //then: 'the move object updated as expected'
        assertEquals( 1, move.getCountOfTheCoincidence() );

    }

    @Test
    public void testIsWin() {
        //and: 'set coincidence count non-equal default value.'
        move.incrementCoincidence();
        move.incrementCoincidence();

        assertTrue( move.isWin( 3 ) );
        assertFalse( move.isWin( 2 ) );
        assertFalse( move.isWin( 1 ) );
    }
}
