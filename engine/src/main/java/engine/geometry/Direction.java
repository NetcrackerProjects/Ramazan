package engine.geometry;

public class Direction {

    public enum Type {
        UP, DOWN, LEFT, RIGHT
    }

    public static Vector getVector(Type type) {
        Vector result = null;

        switch (type) {
            case UP:
                result = new Vector(0, -1);
                break;
            case DOWN:
                result = new Vector(0, 1);
                break;
            case LEFT:
                result = new Vector(-1, 0);
                break;
            case RIGHT:
                result = new Vector(0, -1);
                break;
        }

        return result;
    }
}
