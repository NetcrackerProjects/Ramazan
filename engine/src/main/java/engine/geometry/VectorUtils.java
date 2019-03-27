package engine.geometry;

public class VectorUtils {

    public static Vector subtract(Vector a, Vector b) {
        return new Vector(a.x - b.x, a.y - b.y);
    }

    public static Vector sum(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    public static Vector scale(Vector a, double alpha) {
        return new Vector(a.x * alpha, a.y * alpha);
    }
}
