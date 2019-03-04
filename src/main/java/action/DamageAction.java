package action;

import object.Damageable;

public class DamageAction implements Action {

    private final Damageable damageable;
    private final int damageAmount;

    public DamageAction(Damageable damageable, int damageAmount) {
        this.damageable = damageable;
        this.damageAmount = damageAmount;
    }

    @Override
    public void doAction() {
        damageable.takeDamage(damageAmount);
    }
}
