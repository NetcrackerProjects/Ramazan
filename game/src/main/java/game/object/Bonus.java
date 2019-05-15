package game.object;

import engine.geometry.Vector;
import engine.geometry.VectorUtils;
import engine.object.GameObject;
import game.object.tank.Tank;

public class Bonus extends GameObject {

    private static final Vector SIZE = new Vector(20, 20);

    private final int heal;

    Bonus(Vector leftTop, int heal, int id) {
        super(leftTop, VectorUtils.sum(leftTop, SIZE), false, id, Type.BONUS);
        this.heal = heal;
    }

    public void applyBonus(Tank tank) {
        tank.heal(heal);
    }
}
