package engine.object;

import engine.geometry.Rectangle;
import engine.geometry.Vector;

import java.util.Objects;

public class GameObject implements Identifiable {

    private final Rectangle body;
    private Vector speed;
    private final int id;
    private final int typeId;
    private boolean solid;

    public GameObject(Vector leftTop, Vector rightBottom, int id, int typeId) {
        this.body = new Rectangle(leftTop, rightBottom);
        this.speed = new Vector(0, 0);
        this.id = id;
        this.typeId = typeId;
        this.solid = false;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public Vector getSpeed() {
        return speed;
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

    public Rectangle getMovedBody() {
        Rectangle movedBody = new Rectangle(body);
        movedBody.shift(speed);
        return movedBody;
    }

    public void accelerate(Vector acceleration) {
        speed.shift(acceleration);
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
}