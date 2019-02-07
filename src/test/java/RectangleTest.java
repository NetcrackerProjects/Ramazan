import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RectangleTest {

    @Test
    public void shouldReturnTrueWhenRectanglesIsIntersected(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(4, 4), new Point(14, 14));

        boolean intersects = rec1.intersects(rec2);

        assertTrue(intersects);
    }

    @Test
    public void shouldReturnFalseWhenRectanglesIsNotIntersected(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(14, 14), new Point(24, 24));

        boolean intersects = rec1.intersects(rec2);

        assertFalse(intersects);
    }

    @Test
    public void shouldReturnTrueWhenRectangleIncludeOther(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(5, 5), new Point(7, 7));

        boolean includes = rec1.includes(rec2);

        assertTrue(includes);
    }

    @Test
    public void shouldReturnFalseWhenRectangleNotIncludeOther(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(5, 5), new Point(11, 11));

        boolean includes = rec1.includes(rec2);

        assertFalse(includes);
    }

    @Test
    public void shouldShiftRectangleCorrectlyWhenShifted(){
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));
        Point shift = new Point(1, 1);
        double beforeX = rect.getLeftTop().x;
        double beforeY = rect.getLeftTop().y;

        rect.shift(shift);

        assertTrue((rect.getLeftTop().x == beforeX + shift.x) && (rect.getLeftTop().y == beforeY + shift.y));
    }

    @Test
    public void shouldWorkCorrectlyWhenSetRectangle(){
        Rectangle rect = new Rectangle();
        Rectangle rectForSetting = new Rectangle(new Point(10, 10), new Point(12, 12));

        rect.setRectangle(rectForSetting);

        assertEquals(rectForSetting, rect);
    }
}
