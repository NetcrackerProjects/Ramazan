package game.action;

import game.object.Bonus;
import game.object.tank.Tank;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
