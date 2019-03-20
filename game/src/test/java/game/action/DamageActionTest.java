package game.action;

import game.object.Damageable;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class DamageActionTest {

    @Test
    public void shouldCallTakeDamageWhenDamageActionIsInvoked() {
        Damageable damageable = mock(Damageable.class);
        DamageAction damageAction = new DamageAction(damageable, 1);

        damageAction.doAction();

        verify(damageable, times(1)).takeDamage(anyInt());
    }
}
