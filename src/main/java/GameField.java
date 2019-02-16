import collision.CollisionManager;
import exception.FailedAddObjectException;
import object.GameObject;
import geometry.Point;
import geometry.Rectangle;
import object.GameObjectManager;
import physic.PhysicManager;

class GameField {

    private CollisionManager collisionManager;
    private GameObjectManager gameObjectManager;
    private PhysicManager physicManager;

    GameField(Point leftTop, Point rightBottom) {
        this.gameObjectManager = new GameObjectManager();
        this.collisionManager = new CollisionManager(gameObjectManager);

        Rectangle field = new Rectangle(leftTop, rightBottom);
        this.physicManager = new PhysicManager(field, gameObjectManager);
    }

    void addObject(GameObject gameObject) throws FailedAddObjectException {
        if (!physicManager.canAddObject(gameObject)) {
            throw new FailedAddObjectException();
        }

        gameObjectManager.addObject(gameObject);
    }

    void update() {
        physicManager.move();
        collisionManager.processCollisions();
    }
}
