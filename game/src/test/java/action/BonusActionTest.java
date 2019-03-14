package action;

import object.Bonus;
import object.Tank;
import org.junit.Test;
import org.mockito.Mockito;

public class BonusActionTest {

    @Test
    public void shouldApplyBonusWhenDoAction() {
        Bonus bonus = Mockito.mock(Bonus.class);
        Tank tank = Mockito.mock(Tank.class);
        BonusAction bonusAction = new BonusAction(tank, bonus);

        bonusAction.doAction();

        Mockito.verify(bonus, Mockito.times(1)).applyBonus(tank);
    }
}
