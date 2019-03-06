package object;

import geometry.Vector;

public class Tank extends GameObject implements Damageable {

    private final static int MAX_HEALTH = 10;

    private int health;

    Tank(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, id, Type.TANK);
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
