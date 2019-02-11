import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RectangleTest {

    @Test
    public void shouldReturnTrueWhenRectanglesIsIntersected(){
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rectangle2 = new Rectangle(new Point(4, 4), new Point(14, 14));

        boolean intersects = rectangle1.intersects(rectangle2);

        assertTrue(intersects);
    }

    @Test
    public void shouldReturnFalseWhenRectanglesIsNotIntersected(){
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rectangle2 = new Rectangle(new Point(14, 14), new Point(24, 24));

        boolean intersects = rectangle1.intersects(rectangle2);

        assertFalse(intersects);
    }

    @Test
    public void shouldReturnTrueWhenRectangleIncludeOther(){
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rectangle2 = new Rectangle(new Point(5, 5), new Point(7, 7));

        boolean includes = rectangle1.includes(rectangle2);

        assertTrue(includes);
    }

    @Test
    public void shouldReturnFalseWhenRectangleNotIncludeOther(){
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rectangle2 = new Rectangle(new Point(5, 5), new Point(11, 11));

        boolean includes = rectangle1.includes(rectangle2);

        assertFalse(includes);
    }

    @Test
    public void shouldShiftRectangleCorrectlyWhenShifted(){
        Rectangle rectangle = new Rectangle(new Point(0, 0), new Point(10, 10));
        Point shift = new Point(1, 1);
        Rectangle expected = new Rectangle(new Point(1, 1), new Point(11, 11));

        rectangle.shift(shift);

        assertEquals(expected, rectangle);
    }

    @Test
    public void shouldWorkCorrectlyWhenCopyRectangle(){
        Rectangle rectangle = new Rectangle();
        Rectangle rectangleForCopying = new Rectangle(new Point(10, 10), new Point(12, 12));

        rectangle.copyRectangle(rectangleForCopying);

        assertEquals(rectangleForCopying, rectangle);
    }
}
