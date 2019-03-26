package game.object;

import engine.geometry.Vector;
import engine.object.GameObject;

public class Tank extends GameObject implements Damageable {

    private final static int MAX_HEALTH = 10;

    private int health;

    Tank(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, true, id, Type.TANK);
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

    void heal(int amount) {
        health = Math.min(health + amount, MAX_HEALTH);
    }
}
