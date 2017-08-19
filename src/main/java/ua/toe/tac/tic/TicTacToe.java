package ua.toe.tac.tic;

import ua.toe.tac.tic.enums.Player;
import ua.toe.tac.tic.enums.Direction;
import ua.toe.tac.tic.model.Point;
import ua.toe.tac.tic.model.TheMove;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static ua.toe.tac.tic.enums.Direction.*;

public class TicTacToe {

    private int height;
    private int width;
    private int coincidenceCount = 3;

    private final String[][] board;

    public static Player run() throws IOException {
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("Please, fill the size of the board. Example: '4x4' ");
        String[] borderSize = reader.readLine().split("x");
        int height = Integer.valueOf(borderSize[0]);
        int width = Integer.valueOf(borderSize[1]);
        System.out.println("Please, fill the count of the coincidence. Example '3' ");
        String expectedCoincidenceCount = reader.readLine();
        TicTacToe game = new TicTacToe( height, width );
        game.setCoincidenceCount( Integer.valueOf( expectedCoincidenceCount ) );
        boolean isPlayerX = true;
        while ( true ) {
            System.out.println("Please, fill the point. Example: '1 0' 4");
            String s = reader.readLine();
            String[] ss = s.split( "x" );
            Point point = new Point(Integer.valueOf(ss[0]),Integer.valueOf(ss[1]) );
            if ( isBorderBoundsOut( point, height, width ) ) {
                System.out.println("The point is out of the border bounds. Please, try again.");
                continue;
            }
            TheMove move = new TheMove( point , isPlayerX ? Player.X : Player.O );
            TheMove resultOfTheMoving = game.move( move );
            if( resultOfTheMoving.getCountOfTheCoincidence() == game.getCoincidenceCount() ) {
                game.finish( resultOfTheMoving );
                return resultOfTheMoving.getPlayer();
            }
            // Change player.
            isPlayerX = resultOfTheMoving.getPlayer() != Player.X;
        }
    }

    private static boolean isBorderBoundsOut( Point point, int height, int width ) {
        return point.getX() >= width ||
                point.getX() < 0 ||
                point.getY() >= height ||
                point.getY() < 0;
    }

    public TicTacToe( int height, int width ) {
        this.height = height;
        this.width = width;
        System.out.println("Welcome to the N x M board tictactoe. ");
        board = setDefaultMatrix( height, width );
        System.out.println( "The game is started" );
        printTheBoard();
    }

    private String[][] setDefaultMatrix( int height, int width ) {
        String[][] matrix = new String[height][width];
        for( int i = 0; i < height; i ++ ) {
            Arrays.fill( matrix[i], "." );
        }
        return matrix;
    }

    private void printTheBoard() {
        System.out.println();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(" " + board[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public TheMove move( TheMove move ) {
        board[move.getPoint().getY()][move.getPoint().getX()] = move.getPlayer().name();
        printTheBoard();
        return findCoincidences(Direction.East, move );
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
                if ( checkBoardBorders( East, move) ) {
                    if ( board[move.getPoint().getY()][move.getPoint().getX() + 1].equals( move.getPlayer().name() )  ) {

                        // check for a coincidence
                        if ( isWin(move) ) return move;

                        //move to next point along to this direction.
                        moveTo( move, 1, 0 );

                        if ( findCoincidences( East, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( West, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                break;
            case West:
                if ( checkBoardBorders( West, move) ) {
                    if ( board[move.getPoint().getY()][move.getPoint().getX() - 1].equals( move.getPlayer().toString() ) ) {

                        // check for a coincidence
                        if ( isWin(move) ) {
                            break;
                        }
                        if( move.getCountOfTheCoincidence() == 3 ) {
                            return move;
                        }

                        //move to next point along to this direction.
                        moveTo( move, -1, 0 );

                        // go to this forward.
                        if ( findCoincidences( West, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                move.setCountOfTheCoincidence( 1 );
            case SouthEast:
                if ( checkBoardBorders( SouthEast, move) ) {
                    if ( board[move.getPoint().getY() + 1][move.getPoint().getX() + 1].equals( move.getPlayer().name() ) ) {
                        // check for a coincidence
                        if ( isWin(move) ) break;

                        //move to next point along to this direction.
                        moveTo( move, 1, 1 );

                        // go to this forward.
                        if ( findCoincidences( SouthEast, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( NorthWest, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                break;
            case NorthWest:
                if ( checkBoardBorders( NorthWest, move) ) {
                    if ( board[move.getPoint().getY() - 1][move.getPoint().getX() - 1].equals( move.getPlayer().name() ) ) {
                        // check for a coincidence
                        if ( isWin(move) ) return move;

                        //move to next point along to this direction.
                        moveTo( move, -1, -1 );

                        // go to this forward.
                        if ( findCoincidences( NorthWest, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                move.setCountOfTheCoincidence( 1 );
            case South:
                if ( checkBoardBorders( South, move) ) {
                    if ( board[move.getPoint().getY() + 1 ][move.getPoint().getX()].equals( move.getPlayer().name() ) ) {
                        // check for a coincidence
                        if ( isWin(move) ) return move;

                        //move to next point along to this direction.
                        moveTo( move, 0, 1 );

                        // go to this forward.
                        if ( findCoincidences( South, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( North, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                break;
            case North:
                if ( checkBoardBorders( North, move) ) {
                    if ( board[move.getPoint().getY() - 1 ][move.getPoint().getX()].equals( move.getPlayer().name() ) ) {
                        // check for a coincidence
                        if ( isWin(move) ) return move;

                        //move to next point along to this direction.
                        moveTo( move, 0, -1 );

                        // go to this forward.
                        if ( findCoincidences( North, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                move.setCountOfTheCoincidence( 1 );
            case SouthWest:
                if ( checkBoardBorders( SouthWest, move) ) {
                    if ( board[move.getPoint().getY() + 1][move.getPoint().getX() - 1].equals( move.getPlayer().name() ) ) {
                        // check for a coincidence
                        if ( isWin(move) ) return move;

                        //move to next point along to this direction.
                        moveTo( move, -1, 1 );

                        // go to this forward.
                        if ( findCoincidences( SouthWest, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
                // set default coincidence and revers.
                if( runReversMove( NorthEast, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                break;
            case NorthEast:
                if ( checkBoardBorders( NorthEast, move) ) {
                    if ( board[move.getPoint().getY() - 1][move.getPoint().getX() + 1].equals( move.getPlayer().name() ) ) {

                        if ( isWin(move) ) return move;

                        //move to next point along to this direction.
                        moveTo( move, 1, -1 );

                        // go to this forward.
                        if ( findCoincidences( NorthEast, move ).getCountOfTheCoincidence() == coincidenceCount ) return move;
                    }
                }
        }
        return move;
    }

    private boolean checkBoardBorders( Direction direction, TheMove move ) {
        switch ( direction ) {
            case East:
                return move.getPoint().getX() + 1 < width;
            case West:
                return 0 <= move.getPoint().getX() - 1;
            case North:
                return 0 <= move.getPoint().getY() - 1;
            case South:
                return move.getPoint().getY() + 1 < height;
            case NorthEast:
                return 0 <= move.getPoint().getY() - 1 && move.getPoint().getX() + 1 < width;
            case NorthWest:
                return 0 <= move.getPoint().getY() - 1 && 0 <= move.getPoint().getX() - 1;
            case SouthEast:
                return move.getPoint().getY() + 1 < height && move.getPoint().getX() + 1 < width;
            case SouthWest:
                return move.getPoint().getY() + 1 < height && 0 <= move.getPoint().getX() - 1;
        }
        return true;
    }

    private TheMove runReversMove(Direction direction, TheMove move ) {
        move.setCountOfTheCoincidence( 1 );
        return findCoincidences( direction, move );
    }

    private void moveTo(TheMove move, int x, int y ) {
        move.getPoint().setX( move.getPoint().getX() + x );
        move.getPoint().setY( move.getPoint().getY() + y );
    }

    private boolean isWin( TheMove move ) {
        move.setCountOfTheCoincidence( move.getCountOfTheCoincidence() + 1 );
        return move.getCountOfTheCoincidence() == coincidenceCount;
    }

    public String[][] getBoard() {
        return board;
    }

    public int getCoincidenceCount() {
        return coincidenceCount;
    }

    public void setCoincidenceCount(int coincidenceCount) {
        this.coincidenceCount = coincidenceCount;
    }

}
