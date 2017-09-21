package ua.toe.tac.tic;

import ua.toe.tac.tic.enums.Player;
import ua.toe.tac.tic.enums.Direction;
import ua.toe.tac.tic.model.Board;
import ua.toe.tac.tic.model.Point;
import ua.toe.tac.tic.model.TheMove;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static ua.toe.tac.tic.enums.Direction.*;

public class TicTacToe {

    private int coincidenceCount = 3;
    private final Board board;

    public static Player run() throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("Please, fill the size of the board. Example: '4x4' ");
        String[] borderSize = reader.readLine().split("x");
        int height = Integer.valueOf(borderSize[0]);
        int width = Integer.valueOf(borderSize[1]);
        System.out.println("Please, fill the count of the coincidence. Example '3' ");
        String expectedCoincidenceCount = reader.readLine();
        TicTacToe game = new TicTacToe( width, height );
        game.setCoincidenceCount( Integer.valueOf( expectedCoincidenceCount ) );
        boolean isPlayerX = true;
        while ( true ) {
            System.out.println("Please, fill the point. Example: '1x0'");
            String s = reader.readLine();
            String[] ss = s.split( "x" );
            Point point = new Point(Integer.valueOf(ss[0]),Integer.valueOf(ss[1]) );
            if ( game.board.checkTheExitOfTheBorders( point ) ) {
                System.out.println("The point is out of the border bounds. Please, try again.");
                continue;
            }
            TheMove move = new TheMove( point , isPlayerX ? Player.X : Player.O );
            TheMove resultOfTheMoving = game.move( move );
            if( resultOfTheMoving.isWin( game.getCoincidenceCount() ) ) {
                game.finish( resultOfTheMoving );
                return resultOfTheMoving.getPlayer();
            }
            // Change player.
            isPlayerX = !isPlayerX;
        }
    }

    public TicTacToe( int width, int height ) {
        System.out.println("Welcome to the "+height+" x " + width + " board tictactoe. ");
        board = new Board( width, height );
        System.out.println( "The game is started" );
        board.printTheBoard();
    }

    public TheMove move( TheMove move ) {
        board.markPoint( move );
        return findCoincidences( Direction.East, move );
    }

    private void finish( TheMove move ) {
        System.out.println("=======================================================");
        System.out.println();
        System.out.println("The winner is Player: <.. "+move.getPlayer().name()+" ..>, Congrats!");
        System.out.println();
        System.out.println("=======================================================");
    }

    private TheMove findCoincidences( Direction direction, TheMove move ) {
        switch ( direction ) {
            case East:
                if ( board.checkTheExitOfTheBorders( East, move) ) {
                    if ( board.isPlayerMarkThisPoint( East, move )  ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo(  1, 0 );

                        if ( findCoincidences( East, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( West, move ).isWin( coincidenceCount ) ) return move;
                break;
            case West:
                if ( board.checkTheExitOfTheBorders( West, move ) ) {
                    if ( board.isPlayerMarkThisPoint( West, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo(  -1, 0 );

                        // go to this forward.
                        if ( findCoincidences( West, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                move.setDefaultCoincidence();
            case SouthEast:
                if ( board.checkTheExitOfTheBorders( SouthEast, move ) ) {
                    if ( board.isPlayerMarkThisPoint( SouthEast, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo(  1, 1 );

                        // go to this forward.
                        if ( findCoincidences( SouthEast, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( NorthWest, move ).isWin( coincidenceCount ) ) return move;
                break;
            case NorthWest:
                if ( board.checkTheExitOfTheBorders( NorthWest, move) ) {
                    if ( board.isPlayerMarkThisPoint( NorthWest, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        // go to this forward.
                        if ( findCoincidences( NorthWest, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                move.setDefaultCoincidence();
            case South:
                if ( board.checkTheExitOfTheBorders( South, move ) ) {
                    if ( board.isPlayerMarkThisPoint( South, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo(  0, 1 );

                        // go to this forward.
                        if ( findCoincidences( South, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( North, move ).isWin( coincidenceCount ) ) return move;
                break;
            case North:
                if ( board.checkTheExitOfTheBorders( North, move ) ) {
                    if ( board.isPlayerMarkThisPoint( North, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo(  0, -1 );

                        // go to this forward.
                        if ( findCoincidences( North, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                move.setDefaultCoincidence();
            case SouthWest:
                if ( board.checkTheExitOfTheBorders( SouthWest, move ) ) {
                    if ( board.isPlayerMarkThisPoint( SouthWest, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo(  -1, 1 );

                        // go to this forward.
                        if ( findCoincidences( SouthWest, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( NorthEast, move ).isWin( coincidenceCount ) ) return move;
                break;
            case NorthEast:
                if ( board.checkTheExitOfTheBorders( NorthEast, move ) ) {
                    if ( board.isPlayerMarkThisPoint( NorthEast, move ) ) {

                        // increment count of the coincidence
                        move.incrementCoincidence();

                        if ( move.isWin( coincidenceCount ) ) return move;

                        //move to next point along to this direction.
                        move.moveTo( 1, -1 );

                        // go to this forward.
                        if ( findCoincidences( NorthEast, move ).isWin( coincidenceCount ) ) return move;
                    }
                }
        }
        return move;
    }

    private TheMove runReversMove(Direction direction, TheMove move ) {
        move.setDefaultCoincidence();
        return findCoincidences( direction, move );
    }

    public int getCoincidenceCount() {
        return coincidenceCount;
    }

    public void setCoincidenceCount(int coincidenceCount) {
        this.coincidenceCount = coincidenceCount;
    }
}
