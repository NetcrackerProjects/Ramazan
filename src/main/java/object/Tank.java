package object;

import geometry.Vector;

public class Tank extends GameObject implements Damageable {

    public final static int TYPE_ID = 0;

    private final static int MAX_HEALTH = 10;

    private int health;

    Tank(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, id, TYPE_ID);
        this.health = MAX_HEALTH;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }
}
