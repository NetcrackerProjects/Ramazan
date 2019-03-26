package game.object;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.object.GameObject;

public class Bullet extends GameObject {

    private final int damage;

    Bullet(Vector leftTop, Vector rightBottom, int damage, int id) {
        super(leftTop, rightBottom, false, id, Type.BULLET);
        this.damage = damage;
    }

    Bullet(Rectangle body, int damage, int id) {
        super(body, false, id, Type.BULLET);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
