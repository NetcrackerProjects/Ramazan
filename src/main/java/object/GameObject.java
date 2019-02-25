package object;

import bonus.Bonus;
import geometry.Vector;
import geometry.Rectangle;

public class GameObject implements Damageable, Projectile, BonusHolder, Deletable, BonusTolerable {

    private final Rectangle body;
    private Vector speed;
    private final boolean permeable;

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

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public boolean isBroken() {
        return false;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public boolean isProjectile() {
        return false;
    }

    @Override
    public Bonus getBonus() {
        return null;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    @Override
    public boolean isMarkForDeletion() {
        return false;
    }

    @Override
    public void setOnDelete() {

    }

    @Override
    public boolean isBonusTolerable() {
        return false;
    }
}