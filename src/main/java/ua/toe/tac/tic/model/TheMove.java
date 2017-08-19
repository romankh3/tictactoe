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

    public int getCountOfTheCoincidence() {

        return countOfTheCoincidence;
    }

    public void setCountOfTheCoincidence(int countOfTheCoincidence) {
        this.countOfTheCoincidence = countOfTheCoincidence;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {

        return point;
    }

    public Player getPlayer() {
        return player;
    }
}
