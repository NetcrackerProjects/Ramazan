package interaction;

import object.GameObject;

import java.util.Objects;

public class Interaction {

    private final GameObject first;
    private final GameObject second;

    public Interaction(GameObject first, GameObject second) {
        this.first = first;
        this.second = second;
    }

    GameObject getFirst() {
        return first;
    }

    GameObject getSecond() {
        return second;
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
