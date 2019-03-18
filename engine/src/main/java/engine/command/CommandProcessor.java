package engine.command;

import engine.action.Action;

import java.util.Collection;
import java.util.HashSet;

public class CommandProcessor {

    public Collection<Action> processCommands(Collection<Command> commands) {
        Collection<Action> actions = new HashSet<>();

        for (Command command : commands) {
            actions.add(command.getAction());
        }

        commands.clear();

        return actions;
    }
}
