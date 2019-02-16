package geometry;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RectangleTest {

    @Test
    public void shouldReturnTrueWhenRectanglesIsIntersected() {
        Rectangle firstRectangle = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle secondRectangle = new Rectangle(new Point(4, 4), new Point(14, 14));

        boolean intersects = firstRectangle.intersects(secondRectangle);

        assertTrue(intersects);
    }

    @Test
    public void shouldReturnFalseWhenRectanglesIsNotIntersected() {
        Rectangle firstRectangle = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle secondRectangle = new Rectangle(new Point(14, 14), new Point(24, 24));

        boolean intersects = firstRectangle.intersects(secondRectangle);

        assertFalse(intersects);
    }

    @Test
    public void shouldReturnTrueWhenRectangleCoversOther() {
        Rectangle firstRectangle = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle secondRectangle = new Rectangle(new Point(5, 5), new Point(7, 7));

        boolean covers = firstRectangle.covers(secondRectangle);

        assertTrue(covers);
    }

    @Test
    public void shouldReturnFalseWhenRectangleDoNotCoverOther() {
        Rectangle firstRectangle = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle secondRectangle = new Rectangle(new Point(5, 5), new Point(11, 11));

        boolean covers = firstRectangle.covers(secondRectangle);

        assertFalse(covers);
    }

    @Test
    public void shouldShiftRectangleCorrectlyWhenShifted() {
        Rectangle rectangle = new Rectangle(new Point(0, 0), new Point(10, 10));
        Point shift = new Point(1, 1);
        Rectangle expected = new Rectangle(new Point(1, 1), new Point(11, 11));

        rectangle.shift(shift);

        assertEquals(expected, rectangle);
    }
}
