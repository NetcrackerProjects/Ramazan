import geometry.Vector;
import object.Bullet;
import object.GameObjectFactory;
import object.Tank;
import object.manager.GameObjectManager;
import physic.PhysicManager;

class GameInitializer {

    private GameObjectManager gameObjectManager;
    private PhysicManager physicManager;

    void initialize(GameObjectManager gameObjectManager, PhysicManager physicManager) {
        this.gameObjectManager = gameObjectManager;
        this.physicManager = physicManager;

        addTank(GameObjectFactory.createTank(new Vector(0, 0), new Vector(1, 1)));
        addBullet(GameObjectFactory.createBullet(new Vector(2, 2), new Vector(3, 3)));
    }

    private void addTank(Tank tank) {
        if (physicManager.canAddObject(tank)) {
            gameObjectManager.addTank(tank);
        }
    }

    private void addBullet(Bullet bullet) {
        if (physicManager.canAddObject(bullet)) {
            gameObjectManager.addBullet(bullet);
        }
    }
}
