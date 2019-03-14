package action;

import object.Damageable;
import org.junit.Test;
import org.mockito.Mockito;

public class DamageActionTest {

    @Test
    public void shouldCallTakeDamageWhenDoAction() {
        Damageable damageable = Mockito.mock(Damageable.class);
        int expectedDamage = 1;
        DamageAction damageAction = new DamageAction(damageable, expectedDamage);

        damageAction.doAction();

        Mockito.verify(damageable, Mockito.times(1)).takeDamage(expectedDamage);
    }
}
