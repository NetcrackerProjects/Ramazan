package game.object.tank;

import engine.geometry.Vector;
import engine.geometry.VectorUtils;
import engine.object.GameObject;
import game.object.Damageable;
import game.object.GameObjectFactory;
import game.object.Type;

public class Tank extends GameObject implements Damageable {

    public static final Vector SIZE = new Vector(30, 30);

    private final static int MAX_HEALTH = 10;

    private int health;

    private final TankWeapon tankWeapon;

    public Tank(Vector leftTop, GameObjectFactory gameObjectFactory, int id) {
        super(leftTop, VectorUtils.sum(leftTop, SIZE), true, id, Type.TANK);
        this.health = MAX_HEALTH;
        this.tankWeapon = new TankWeapon(getBody(), id, gameObjectFactory);
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

    public void rotateWeapon() {
        if (getSpeed().equals(new Vector(0, 0))) {
            return;
        }

        tankWeapon.setDirection(VectorUtils.unitVector(getSpeed()));
    }
}
