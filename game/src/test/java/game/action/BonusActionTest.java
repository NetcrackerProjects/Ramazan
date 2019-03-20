package game.action;

import game.object.Bonus;
import game.object.Tank;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BonusActionTest {

    @Test
    public void shouldApplyWhenBonusActionIsInvoked() {
        Bonus bonus = mock(Bonus.class);
        Tank tank = mock(Tank.class);
        BonusAction bonusAction = new BonusAction(tank, bonus);

        bonusAction.doAction();

        verify(bonus, times(1)).applyBonus(tank);
    }
}
