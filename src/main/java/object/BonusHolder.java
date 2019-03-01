package object;

import bonus.Bonus;
import geometry.Vector;

public class BonusHolder extends GameObject {

    public static final int TYPE_ID = 2;

    private final Bonus bonus;

    public BonusHolder(Vector leftTop, Vector rightBottom, Bonus bonus, int id) {
        super(leftTop, rightBottom, id, TYPE_ID);
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }
}
