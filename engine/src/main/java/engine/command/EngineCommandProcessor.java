package engine.command;

import engine.action.Action;

import java.util.Collection;
import java.util.HashSet;

public class EngineCommandProcessor {

    public Collection<Action> processCommands(Collection<EngineCommand> engineCommands) {
        Collection<Action> actions = new HashSet<>();

        for (EngineCommand engineCommand : engineCommands) {
            actions.add(engineCommand.getAction());
        }

        engineCommands.clear();

        return actions;
    }
}
