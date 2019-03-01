package object;

import geometry.Vector;

public class Bullet extends GameObject {

    public final static int TYPE_ID = 1;
    private final int damage;

    Bullet(Vector leftTop, Vector rightBottom, int damage, int id) {
        super(leftTop, rightBottom, id, TYPE_ID);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
