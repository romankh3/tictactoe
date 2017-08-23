package ua.toe.tac.tic.model;

import org.junit.Test;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.Assert.assertEquals;

/**
 * Unit-level testing for {@link Point} object
 */
public class PointUnitTest {

    @Test
    public void testCorrentCreate() {
        int height = current().nextInt( 10, 15 );
        int width = current().nextInt( 10, 15 );

        Point point = new Point(width, height);
        assertEquals( width, point.getX() );
        assertEquals( height, point.getY() );

        int updatedHeight = current().nextInt( 16, 20 );
        int updatedWidth = current().nextInt( 16, 20 );


        point.setX( updatedWidth );
        point.setY( updatedHeight );

        assertEquals( updatedWidth, point.getX() );
        assertEquals( updatedHeight, point.getY() );
    }
}
