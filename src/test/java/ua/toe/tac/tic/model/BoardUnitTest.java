package ua.toe.tac.tic.model;

import org.junit.Test;
import ua.toe.tac.tic.enums.Player;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ua.toe.tac.tic.enums.Direction.Default;
import static ua.toe.tac.tic.enums.Direction.East;
import static ua.toe.tac.tic.enums.Direction.West;

/**
 * Unit-level testing for {@link Board} object.
 */
public class BoardUnitTest {

    @Test
    public void testBoardObject() {
        int height = ThreadLocalRandom.current().nextInt( 10, 15 );
        int width = ThreadLocalRandom.current().nextInt( 10, 15 );
        Board board = new Board( width, height );
        TheMove move = new TheMove( new Point(0,0), Player.X );
        board.markPoint( move );
        assertTrue( board.isPlayerMarkThisPoint( Default, move ) );
        assertFalse( board.checkTheExitOfTheBorders( West, move ) );
        assertTrue( board.checkTheExitOfTheBorders( East, move ) );
        assertFalse( board.checkTheExitOfTheBorders( new Point(width-5, height-5) ) );
        assertTrue( board.checkTheExitOfTheBorders( new Point( width, height ) ) );
    }
}
