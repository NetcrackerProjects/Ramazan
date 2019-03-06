import geometry.Vector;
import object.Bullet;
import object.GameObjectFactory;
import object.Tank;
import object.manager.GameObjectManager;
import physic.PhysicManager;

class GameInitializer {

    static void initialize(PhysicManager physicManager, GameObjectManager gameObjectManager) {
        addTank(GameObjectFactory.createTank(new Vector(0, 0), new Vector(1, 1)),
                physicManager, gameObjectManager);
        addBullet(GameObjectFactory.createBullet(new Vector(2, 2), new Vector(3, 3)),
                physicManager, gameObjectManager);

    }

    private static void addTank(Tank tank, PhysicManager physicManager, GameObjectManager gameObjectManager) {
        if (physicManager.canAddObject(tank)) {
            gameObjectManager.addTank(tank);
        }
    }

    private static void addBullet(Bullet bullet, PhysicManager physicManager, GameObjectManager gameObjectManager) {
        if (physicManager.canAddObject(bullet)) {
            gameObjectManager.addBullet(bullet);
        }
    }
}
