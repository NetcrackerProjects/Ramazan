package engine.geometry;

import junit.framework.TestCase;
import org.junit.Test;

public class VectorTest {

    @Test
    public void shouldWorkCorrectlyWhenShift() {
        Vector vector = new Vector(0, 0);
        Vector shift = new Vector(1, 2);

        vector.shift(shift);

        TestCase.assertEquals(vector, shift);
    }
}
