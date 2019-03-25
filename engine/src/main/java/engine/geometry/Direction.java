package engine.geometry;

public class Direction {

    public enum Type {
        UP, DOWN, LEFT, RIGHT
    }

    public static Vector getVector(Type type) {
        switch (type) {
            case UP:
                return new Vector(0, -1);
            case DOWN:
                return new Vector(0, 1);
            case LEFT:
                return new Vector(-1, 0);
            case RIGHT:
                return new Vector(1, 0);
        }

        throw new IllegalArgumentException("unrecognized type");
    }
}
