package engine.geometry;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RectangleTest {

    private Rectangle firstRectangle;

    @Before
    public void setup() {
        this.firstRectangle = new Rectangle(new Vector(0, 0), new Vector(10, 10));
    }

    @Test
    public void shouldReturnTrueWhenRectanglesIsIntersected() {
        Rectangle secondRectangle = new Rectangle(new Vector(4, 4), new Vector(14, 14));

        boolean intersects = firstRectangle.intersects(secondRectangle);

        assertTrue(intersects);
    }

    @Test
    public void shouldReturnFalseWhenRectanglesIsNotIntersected() {
        Rectangle secondRectangle = new Rectangle(new Vector(14, 14), new Vector(24, 24));

        boolean intersects = firstRectangle.intersects(secondRectangle);

        assertFalse(intersects);
    }

    @Test
    public void shouldReturnTrueWhenRectangleCoversOther() {
        Rectangle secondRectangle = new Rectangle(new Vector(5, 5), new Vector(7, 7));

        boolean covers = firstRectangle.covers(secondRectangle);

        assertTrue(covers);
    }

    @Test
    public void shouldReturnFalseWhenRectangleDoNotCoverOther() {
        Rectangle secondRectangle = new Rectangle(new Vector(5, 5), new Vector(11, 11));

        boolean covers = firstRectangle.covers(secondRectangle);

        assertFalse(covers);
    }

    @Test
    public void shouldShiftRectangleCorrectlyWhenShifted() {
        Vector shift = new Vector(1, 1);
        Rectangle expected = new Rectangle(new Vector(1, 1), new Vector(11, 11));

        firstRectangle.shift(shift);

        assertEquals(expected, firstRectangle);
    }
}
