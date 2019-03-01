package action;

import bonus.Bonus;
import object.GameObject;

public class BonusObjectAction implements Action {

    private final GameObject gameObject;
    private final Bonus bonus;

    public BonusObjectAction(GameObject gameObject, Bonus bonus) {
        this.gameObject = gameObject;
        this.bonus = bonus;
    }

    @Override
    public void doAction() {
        bonus.applyBonus(gameObject);
    }
}
