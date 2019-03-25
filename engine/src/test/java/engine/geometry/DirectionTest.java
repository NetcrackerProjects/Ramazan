package engine.geometry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    @Test
    public void shouldReturnCorrectVectorWhenCalledWithRightType() {
        Vector expected = new Vector(1, 0);

        Vector result = Direction.getVector(Direction.Type.RIGHT);

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCorrectVectorWhenCalledWithLeftType() {
        Vector expected = new Vector(-1, 0);

        Vector result = Direction.getVector(Direction.Type.LEFT);

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCorrectVectorWhenCalledWithUpType() {
        Vector expected = new Vector(0, -1);

        Vector result = Direction.getVector(Direction.Type.UP);

        assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCorrectVectorWhenCalledWithDownType() {
        Vector expected = new Vector(0, 1);

        Vector result = Direction.getVector(Direction.Type.DOWN);

        assertEquals(expected, result);
    }
}
