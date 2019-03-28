package game.action;

import game.object.Damageable;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyInt;

public class DamageActionTest {

    @Test
    public void shouldCallTakeDamageWhenDamageActionIsInvoked() {
        Damageable damageable = Mockito.mock(Damageable.class);
        DamageAction damageAction = new DamageAction(damageable, 1);

        damageAction.doAction();

        Mockito.verify(damageable, Mockito.times(1)).takeDamage(anyInt());
    }
}
