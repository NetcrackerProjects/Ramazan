import collision.CollisionManager;
import exception.ObjectAddException;
import object.GameObject;
import geometry.Vector;
import geometry.Rectangle;
import object.GameObjectManager;
import physic.PhysicManager;

class GameField {

    private final CollisionManager collisionManager;
    private final GameObjectManager gameObjectManager;
    private final PhysicManager physicManager;

    GameField(Vector leftTop, Vector rightBottom) {
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new CollisionManager(gameObjectManager);

        Rectangle field = new Rectangle(leftTop, rightBottom);
        this.physicManager = new PhysicManager(field, gameObjectManager);
    }

    void addObject(GameObject gameObject) throws ObjectAddException {
        if (!physicManager.canAddObject(gameObject)) {
            throw new ObjectAddException();
        }

        gameObjectManager.addObject(gameObject);
    }

    void update() {
        physicManager.move();
        collisionManager.processCollisions();
    }
}
