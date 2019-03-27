package game.object;

import engine.geometry.Vector;
import engine.object.GameObject;
import game.object.tank.Tank;

public class Bonus extends GameObject {

    private final int heal;

    Bonus(Vector leftTop, Vector rightBottom, int heal, int id) {
        super(leftTop, rightBottom, false, id, Type.BONUS);
        this.heal = heal;
    }

    public void applyBonus(Tank tank) {
        tank.heal(heal);
    }
}
