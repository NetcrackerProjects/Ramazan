package event;

import action.Action;
import object.GameObjectManager;

import java.util.Collection;

public class EventManager {

    private final EventDetector eventDetector;
    private final EventProcessor eventProcessor;

    public EventManager(GameObjectManager gameObjectManager) {
        this.eventDetector = new EventDetector(gameObjectManager);
        this.eventProcessor = new EventProcessor();
    }

    public Collection<Action> getActions() {
        Collection<Event> events = eventDetector.detectEvents();
        return eventProcessor.processEvents(events);
    }
}
