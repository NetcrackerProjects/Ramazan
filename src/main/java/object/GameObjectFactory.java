package object;

import exception.GameObjectFactoryException;
import geometry.Vector;

public class GameObjectFactory {

    private static int currentId = 0;

    public static GameObject create(String name, Vector leftTop, Vector rightBottom) throws GameObjectFactoryException {
        switch (name) {
            case "tank":
                return new Tank(leftTop, rightBottom, getNewId());
            case "bullet":
                return new Bullet(leftTop, rightBottom, 1, getNewId());
        }
        throw new GameObjectFactoryException();
    }

    private static int getNewId() {
        return currentId++;
    }
}
