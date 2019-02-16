package collision;

import object.GameObject;

import java.util.Objects;

public class Collision {

    private GameObject firstObject;
    private GameObject secondObject;

    public Collision(GameObject firstObject, GameObject secondObject) {
        this.firstObject = firstObject;
        this.secondObject = secondObject;
    }

    public void carryOut() {
        firstObject.intersectGameObject();
        secondObject.intersectGameObject();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Collision collision = (Collision) object;
        return (Objects.equals(firstObject, collision.firstObject) &&
                Objects.equals(secondObject, collision.secondObject)) ||
                (Objects.equals(firstObject, collision.secondObject) &&
                        Objects.equals(secondObject, collision.firstObject));
    }
}
