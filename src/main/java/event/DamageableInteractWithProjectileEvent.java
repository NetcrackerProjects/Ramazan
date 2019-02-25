package event;

import action.Action;
import action.DamageObjectAction;
import action.DeleteObjectAction;
import object.Damageable;
import object.GameObject;

import java.util.Collection;
import java.util.HashSet;

class DamageableInteractWithProjectileEvent implements Event {

    private final Damageable damageable;
    private final GameObject projectile;

    DamageableInteractWithProjectileEvent(Damageable damageable, GameObject projectile) {
        this.damageable = damageable;
        this.projectile = projectile;
    }

    @Override
    public Collection<Action> getActions() {
        Collection<Action> actions = new HashSet<>();
        actions.add(new DamageObjectAction(damageable, projectile.getDamage()));
        actions.add(new DeleteObjectAction(projectile));
        return actions;
    }
}
