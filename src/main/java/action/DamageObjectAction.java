package action;

import object.Damageable;

public class DamageObjectAction implements Action {

    private final Damageable damageable;
    private final int damageAmount;

    public DamageObjectAction(Damageable damageable, int damageAmount) {
        this.damageable = damageable;
        this.damageAmount = damageAmount;
    }

    @Override
    public void doAction() {
        damageable.takeDamage(damageAmount);
    }
}
