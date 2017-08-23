package ua.toe.tac.tic.model;

import ua.toe.tac.tic.enums.Direction;
import java.util.Arrays;

public class Board {

    private final int height;
    private final int width;
    private final String[][] board;

    public Board( int width, int height ) {
        this.height = height;
        this.width = width;
        board = setDefaultMatrix( height, width );
    }

    public void markPoint( TheMove move ) {
        board[move.getPoint().getY()][move.getPoint().getX()] = move.getPlayer().name();
        printTheBoard();
    }

    private String[][] setDefaultMatrix( int height, int width ) {
        String[][] matrix = new String[height][width];
        for( int i = 0; i < height; i ++ ) {
            Arrays.fill( matrix[i], "." );
        }
        return matrix;
    }

    public boolean checkTheExitOfTheBorders( Point point ) {
        return point.getX() >= width ||
                point.getX() < 0 ||
                point.getY() >= height ||
                point.getY() < 0;
    }

    public void printTheBoard() {
        System.out.println();
        for (String[] aBoard : board) {
            for (int x = 0; x < board[0].length; x++) {
                System.out.print(" " + aBoard[x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isPlayerMarkThisPoint( Direction direction, TheMove move ) {
        switch ( direction ) {
            case East:
                return board[move.getPoint().getY()][move.getPoint().getX() + 1].equals( move.getPlayer().name() );
            case West:
                return board[move.getPoint().getY()][move.getPoint().getX() - 1].equals( move.getPlayer().toString() );
            case SouthEast:
                return board[move.getPoint().getY() + 1][move.getPoint().getX() + 1].equals( move.getPlayer().name() );
            case NorthWest:
                return board[move.getPoint().getY() - 1][move.getPoint().getX() - 1].equals( move.getPlayer().name() );
            case South:
                return board[move.getPoint().getY() + 1 ][move.getPoint().getX()].equals( move.getPlayer().name() );
            case North:
                return board[move.getPoint().getY() - 1 ][move.getPoint().getX()].equals( move.getPlayer().name() );
            case SouthWest:
                return board[move.getPoint().getY() + 1][move.getPoint().getX() - 1].equals( move.getPlayer().name() );
            case NorthEast:
                return board[move.getPoint().getY() - 1][move.getPoint().getX() + 1].equals( move.getPlayer().name() );
            default:
                return board[move.getPoint().getY()][move.getPoint().getX()].equals( move.getPlayer().name() );
        }

    }

    public boolean checkTheExitOfTheBorders( Direction direction, TheMove move ) {
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
}
