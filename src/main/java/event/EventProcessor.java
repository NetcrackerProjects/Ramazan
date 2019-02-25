package event;

import action.Action;

import java.util.Collection;
import java.util.HashSet;

class EventProcessor {

    Collection<Action> processEvents(Collection<Event> events) {
        Collection<Action> actions = new HashSet<>();
        for (Event event: events) {
            actions.addAll(event.getActions());
        }
        return actions;
    }
}
