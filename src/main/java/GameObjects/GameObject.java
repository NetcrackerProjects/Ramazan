package GameObjects;

import Geometry.Point;
import Geometry.Rectangle;

public class GameObject {

    private Rectangle body;
    private Point speed;
    private boolean permeable;

    public GameObject(Point leftTop, Point rightBottom) {
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Point(0, 0);
        this.permeable = false;
    }

    public Point getSpeed() {
        return speed;
    }

    public void setSpeed(Point speed) {
        this.speed = speed;
    }

    public Point getPos() {
        return body.getTopLeft();
    }

    public Rectangle getBody() {
        return body;
    }

    public boolean doesIntersect(Rectangle rectangle) {
        return body.intersects(rectangle);
    }

    public boolean isPermeable() {
        return permeable;
    }

    public void setPermeable() {
        this.permeable = true;
    }

    public void move() {
        body.shift(speed);
    }

    public void intersectGameObject() {
    }
}
