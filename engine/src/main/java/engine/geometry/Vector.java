package engine.geometry;

public class Vector {

    double x;
    double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    public void shift(Vector shift) {
        x += shift.x;
        y += shift.y;
    }

    public void scale(double alpha) {
        x *= alpha;
        y *= alpha;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object.getClass() != Vector.class) {
            return false;
        }

        Vector p = (Vector) object;
        return (p.x == x && p.y == y);
    }
}
