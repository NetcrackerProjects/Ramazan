package object;

import geometry.Rectangle;
import geometry.Vector;

public class GameObject implements Identifiable {

    private final Rectangle body;
    private Vector speed;
    private final int id;
    private final int typeId;

    public GameObject(Vector leftTop, Vector rightBottom, int id, int typeId) {
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Vector(0, 0);
        this.id = id;
        this.typeId = typeId;
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

    public void move() {
        body.shift(speed);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }
}