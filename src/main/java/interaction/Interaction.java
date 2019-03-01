package interaction;

import object.GameObject;

public class Interaction {

    private final GameObject first;
    private final GameObject second;

    public Interaction(GameObject first, GameObject second) {
        this.first = first;
        this.second = second;
    }

    public GameObject getFirst() {
        return first;
    }

    public GameObject getSecond() {
        return second;
    }
}
