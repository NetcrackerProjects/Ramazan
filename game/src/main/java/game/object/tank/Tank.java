package game.object.tank;

import engine.geometry.Vector;
import engine.object.GameObject;
import game.object.Damageable;
import game.object.Type;

public class Tank extends GameObject implements Damageable {

    private final static int MAX_HEALTH = 10;

    private int health;

    private TankWeapon tankWeapon;

    public Tank(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, true, id, Type.TANK);
        this.health = MAX_HEALTH;
        this.tankWeapon = new TankWeapon(getBody(), getSpeed());
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public void heal(int amount) {
        health = Math.min(health + amount, MAX_HEALTH);
    }

    public TankWeapon getTankWeapon() {
        return tankWeapon;
    }
}
