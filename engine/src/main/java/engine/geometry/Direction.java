package engine.geometry;

public class Direction {

    public enum Type {
        UP, DOWN, LEFT, RIGHT
    }

    public static Vector getVector(Type type) {
        Vector result = new Vector(0, 0);

        switch (type) {
            case UP:
                result.y = -1;
                break;
            case DOWN:
                result.y = 1;
                break;
            case LEFT:
                result.x = -1;
                break;
            case RIGHT:
                result.x = 1;
                break;
        }

        return result;
    }
}
