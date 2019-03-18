package engine.action;

import java.util.Collection;

public class ActionManager {

    public void processActions(Collection<Action> actions) {
        for (Action action : actions) {
            action.doAction();
        }
    }
}
