package action;

import bonus.Bonus;
import object.GameObject;

public class BonusAction implements Action {

    private final GameObject gameObject;
    private final Bonus bonus;

    public BonusAction(GameObject gameObject, Bonus bonus) {
        this.gameObject = gameObject;
        this.bonus = bonus;
    }

    @Override
    public void doAction() {
        bonus.applyBonus(gameObject);
    }
}
