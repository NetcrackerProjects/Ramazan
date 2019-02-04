import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class RectangleTest {

    @Test
    public void should_returnTrue_when_rectanglesIntersected(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(4, 4), new Point(10, 10));

        boolean intersects = rec1.intersects(rec2);

        assertTrue(intersects);
    }

    @Test
    public void should_returnFalse_when_rectanglesNotIntersected(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(14, 14), new Point(10, 10));

        boolean intersects = rec1.intersects(rec2);

        assertFalse(intersects);
    }

    @Test
    public void should_returnTrue_when_rectangleIncludeOther(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(5, 5), new Point(2, 2));

        boolean includes = rec1.includes(rec2);

        assertTrue(includes);
    }

    @Test
    public void should_returnFalse_when_rectangleNotIncludeOther(){
        Rectangle rec1 = new Rectangle(new Point(0, 0), new Point(10, 10));
        Rectangle rec2 = new Rectangle(new Point(5, 5), new Point(6, 6));

        boolean includes = rec1.includes(rec2);

        assertFalse(includes);
    }

    @Test
    public void should_shiftRectangleCorrectly_when_shift(){
        Rectangle rect = new Rectangle(new Point(0, 0), new Point(10, 10));
        Point shift = new Point(1, 1);
        double beforeX = rect.getPos().x;
        double beforeY = rect.getPos().y;

        rect.shift(shift);

        assertTrue((rect.getPos().x == beforeX + shift.x) && (rect.getPos().y == beforeY + shift.y));
    }
}
