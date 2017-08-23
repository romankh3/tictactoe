package ua.toe.tac.tic;

import org.junit.Assert;
import org.junit.Test;
import ua.toe.tac.tic.enums.Player;
import ua.toe.tac.tic.model.Point;
import ua.toe.tac.tic.model.TheMove;

/**
 * Unit-level testing for {@link TicTacToe} object.
 */
public class TicTacToeUnitTest {

    private TicTacToe ticTacToe = new TicTacToe( 3, 3 );


    @Test
    public void testCorrectGamePlayWithWinnerX() {
        // simulate game with known winner.
        TheMove move = new TheMove(new Point(1,1),Player.X);
        ticTacToe.move(move);

        move = new TheMove(new Point(1,0),Player.O);
        ticTacToe.move(move);

        move = new TheMove(new Point(0,2),Player.X);
        ticTacToe.move(move);

        move = new TheMove(new Point(2,0),Player.O);
        ticTacToe.move(move);

        move = new TheMove(new Point(0,0),Player.X);
        ticTacToe.move(move);

        move = new TheMove(new Point(0,1),Player.O);
        ticTacToe.move(move);

        move = new TheMove(new Point(2,2),Player.X);
        ticTacToe.move(move);

        Assert.assertTrue( move.isWin( ticTacToe.getCoincidenceCount() ) );
        Assert.assertEquals( Player.X, move.getPlayer() );
    }

    @Test
    public void testCorrectGamePlayWithWinnerO() {
        // simulate game with known winner.
        TheMove move = new TheMove(new Point(1,1),Player.X);
        ticTacToe.move(move);

        move = new TheMove(new Point(1,0),Player.O);
        ticTacToe.move(move);

        move = new TheMove(new Point(0,2),Player.X);
        ticTacToe.move(move);

        move = new TheMove(new Point(2,0),Player.O);
        ticTacToe.move(move);

        move = new TheMove(new Point(2,2),Player.X);
        ticTacToe.move(move);

        move = new TheMove(new Point(0,0),Player.O);
        ticTacToe.move(move);

        Assert.assertTrue( move.isWin( ticTacToe.getCoincidenceCount() ) );
        Assert.assertEquals( Player.O, move.getPlayer() );

    }
}
