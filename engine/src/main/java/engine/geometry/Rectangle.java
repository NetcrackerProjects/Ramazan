package engine.geometry;

import java.util.Objects;

public class Rectangle {

    private final Vector topLeft;
    private final Vector bottomRight;

    public Rectangle(Vector topLeft, Vector bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Rectangle(Rectangle rectangle) {
        this.topLeft = new Vector(rectangle.topLeft);
        this.bottomRight = new Vector(rectangle.bottomRight);
    }

    public Vector getTopLeft() {
        return topLeft;
    }

    public boolean intersects(Rectangle rectangle) {
        if (rectangle.topLeft.x > bottomRight.x) {
            return false;
        }

        if (rectangle.topLeft.y > bottomRight.y) {
            return false;
        }

        if (rectangle.bottomRight.x < topLeft.x) {
            return false;
        }

        return !(rectangle.bottomRight.y < topLeft.y);
    }

    public boolean covers(Rectangle rectangle) {
        if (rectangle.topLeft.x < topLeft.x) {
            return false;
        }

        if (rectangle.topLeft.y < topLeft.y) {
            return false;
        }

        if (rectangle.bottomRight.x > bottomRight.x) {
            return false;
        }

        return !(rectangle.bottomRight.y > bottomRight.y);
    }

    public void shift(Vector shift) {
        topLeft.shift(shift);
        bottomRight.shift(shift);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) object;

        return Objects.equals(topLeft, rectangle.topLeft) && Objects.equals(bottomRight, rectangle.bottomRight);
    }
}