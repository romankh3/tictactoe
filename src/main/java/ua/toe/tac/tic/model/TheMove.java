package ua.toe.tac.tic.model;

import ua.toe.tac.tic.enums.Player;

public class TheMove {

    private Point point;
    private int countOfTheCoincidence = 1;
    private Player player;


    public TheMove( Point point, Player player ) {
        this.point = point;
        this.player = player;
    }

    public void incrementCoincidence() {
        ++countOfTheCoincidence;
    }

    public void setDefaultCoincidence() {
        countOfTheCoincidence = 1;
    }

    public boolean isWin( int requiredCoincidence ) {
        return countOfTheCoincidence == requiredCoincidence;
    }

    public void moveTo( int x, int y ) {
        point.setX( point.getX() + x );
        point.setY( point.getY() + y );
    }

    public int getCountOfTheCoincidence() {
        return countOfTheCoincidence;
    }

    public Point getPoint() {
        return point;
    }

    public Player getPlayer() {
        return player;
    }
}
