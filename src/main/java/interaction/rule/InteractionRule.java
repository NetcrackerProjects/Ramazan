package interaction.rule;

import action.Action;
import exception.WrongObjectIdException;
import interaction.Interaction;
import object.GameObject;

import java.util.Collection;

public interface InteractionRule<T extends GameObject, V extends GameObject> {

    Collection<Action> getActions(Interaction interaction);

    T getFirstObject(Interaction interaction) throws WrongObjectIdException;

    V getSecondObject(Interaction interaction) throws WrongObjectIdException;
}
