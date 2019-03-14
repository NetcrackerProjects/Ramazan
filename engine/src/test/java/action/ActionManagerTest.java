package action;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.HashSet;

public class ActionManagerTest {

    @Test
    public void shouldCallDoActionWhenProcessActions() {
        ActionManager actionManager = new ActionManager();
        Collection<Action> actions = new HashSet<>();
        Action action = Mockito.mock(Action.class);
        actions.add(action);

        actionManager.processActions(actions);

        Mockito.verify(action, Mockito.times(1)).doAction();
    }
}
