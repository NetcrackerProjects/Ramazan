package game.object;

import engine.geometry.Vector;
import engine.object.GameObject;

public class Bullet extends GameObject {

    private final int damage;

    Bullet(Vector leftTop, Vector rightBottom, int damage, int id) {
        super(leftTop, rightBottom, id, Type.BULLET);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
