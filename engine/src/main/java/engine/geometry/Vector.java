package engine.geometry;

public class Vector {

    double x;
    double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
    }

    void shift(Vector shift) {
        x += shift.x;
        y += shift.y;
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
