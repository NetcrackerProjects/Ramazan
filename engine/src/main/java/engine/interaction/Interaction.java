package engine.interaction;

import engine.object.GameObject;

import java.util.Objects;

public class Interaction {

    private final GameObject first;
    private final GameObject second;
    private final InteractionType interactionType;

    public Interaction(GameObject first, GameObject second) {
        this.first = first;
        this.second = second;
        this.interactionType = new InteractionType(first.getTypeId(), second.getTypeId());
    }

    public GameObject getFirst() {
        return first;
    }

    public GameObject getSecond() {
        return second;
    }

    InteractionType getInteractionType() {
        return interactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interaction that = (Interaction) o;
        return (Objects.equals(first, that.first) &&
                Objects.equals(second, that.second)) ||
                (Objects.equals(first, that.second) &&
                        Objects.equals(second, that.first));
    }

    @Override
    public int hashCode() {
        int res = 17;
        res = res * 31 + Math.min(first.hashCode(), second.hashCode());
        res = res * 31 + Math.max(first.hashCode(), second.hashCode());
        return res;
    }
}
