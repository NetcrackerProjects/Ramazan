package object;

import bonus.Bonus;
import geometry.Vector;

public class BonusHolder extends GameObject {

    private final Bonus bonus;

    public BonusHolder(Vector leftTop, Vector rightBottom, Bonus bonus, int id) {
        super(leftTop, rightBottom, id, Type.BONUS);
        this.bonus = bonus;
    }

    public Bonus getBonus() {
        return bonus;
    }
}
