package engine.action;

import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActionManagerTest {

    @Test
    public void shouldCallDoActionWhenProcessActions() {
        ActionManager actionManager = new ActionManager();
        Collection<Action> actions = new HashSet<>();
        Action action = mock(Action.class);
        actions.add(action);

        actionManager.processActions(actions);

        verify(action, times(1)).doAction();
    }
}
