package action;

import object.Bonus;
import object.Tank;

public class BonusAction implements Action {

    private final Tank tank;
    private final Bonus bonus;

    public BonusAction(Tank tank, Bonus bonus) {
        this.tank = tank;
        this.bonus = bonus;
    }

    @Override
    public void doAction() {
        bonus.applyBonus(tank);
    }
}
