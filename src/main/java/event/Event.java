package event;

import action.Action;

import java.util.Collection;

interface Event {

    Collection<Action> getActions();
}
