package engine.geometry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorUtilsTest {

    @Test
    public void shouldWorkCorrectlyWhenSum() {
        Vector a = new Vector(10, 2);
        Vector b = new Vector(5, 1);
        Vector expected = new Vector(15, 3);

        Vector sum = VectorUtils.sum(a, b);

        assertEquals(expected, sum);
    }

    @Test
    public void shouldWorkCorrectlyWhenSubtract() {
        Vector a = new Vector(10, 2);
        Vector b = new Vector(5, 1);
        Vector expected = new Vector(5, 1);

        Vector subtraction = VectorUtils.subtract(a, b);

        assertEquals(expected, subtraction);
    }

    @Test
    public void shouldWorkCorrectlyWhenScale() {
        Vector a = new Vector(10, 2);
        Vector expected = new Vector(5, 1);

        Vector subtraction = VectorUtils.scale(a, 0.5);

        assertEquals(expected, subtraction);
    }
}
