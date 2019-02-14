package Geometry;

import java.util.Objects;

public class Rectangle {

    private Point topLeft;
    private Point bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Rectangle(Rectangle rectangle) {
        this.topLeft = new Point(rectangle.topLeft);
        this.bottomRight = new Point(rectangle.bottomRight);
    }

    public Point getTopLeft() {
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

        if (rectangle.bottomRight.y < topLeft.y) {
            return false;
        }

        return true;
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

        if (rectangle.bottomRight.y > bottomRight.y) {
            return false;
        }

        return true;
    }

    public void shift(Point shift) {
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