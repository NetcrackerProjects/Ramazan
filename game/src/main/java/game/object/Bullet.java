package game.object;

import engine.geometry.Rectangle;
import engine.object.GameObject;

public class Bullet extends GameObject {

    private final int damage;
    private final int tankId;

    Bullet(Rectangle body, int damage, int id, int tankId) {
        super(body, false, id, Type.BULLET);
        this.damage = damage;
        this.tankId = tankId;
    }

    public int getDamage() {
        return damage;
    }

    public int getTankId() {
        return tankId;
    }
}
