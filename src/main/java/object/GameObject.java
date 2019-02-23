package object;

import geometry.Vector;
import geometry.Rectangle;

public class GameObject {

    private final Rectangle body;
    private Vector speed;
    private boolean permeable;

    public GameObject(Vector leftTop, Vector rightBottom) {
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Vector(0, 0);
        this.permeable = false;
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public Vector getPosition() {
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

    public void move() {
        body.shift(speed);
    }

    public void intersectGameObject() {
    }
}
