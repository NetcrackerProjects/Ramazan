package object.manager;

import object.Bullet;
import object.GameObject;
import object.Tank;

public class GameObjectManager {

    private final PhysicObjectManager physicObjectManager;

    private final TankObjectManager tankObjectManager;
    private final BulletObjectManager bulletObjectManager;
    private final BonusObjectManager bonusObjectManager;

    public GameObjectManager() {
        this.physicObjectManager = new PhysicObjectManager();
        this.tankObjectManager = new TankObjectManager();
        this.bulletObjectManager = new BulletObjectManager();
        this.bonusObjectManager = new BonusObjectManager();
    }

    public PhysicObjectManager getPhysicObjectManager() {
        return physicObjectManager;
    }

    public void removeObject(GameObject gameObject) {
        physicObjectManager.removeObject(gameObject);

        if (tankObjectManager.getTypeId() == gameObject.getTypeId()) {
            tankObjectManager.removeById(gameObject.getId());
        }

        if (bulletObjectManager.getTypeId() == gameObject.getTypeId()) {
            bulletObjectManager.removeById(gameObject.getId());
        }

        if (bonusObjectManager.getTypeId() == gameObject.getTypeId()) {
            bonusObjectManager.removeById(gameObject.getId());
        }
    }

    public void addTank(Tank tank) {
        tankObjectManager.addObject(tank);
        physicObjectManager.addObject(tank);
    }

    public void addBullet(Bullet bullet) {
        bulletObjectManager.addObject(bullet);
        physicObjectManager.addObject(bullet);
    }

    public TankObjectManager getTankObjectManager() {
        return tankObjectManager;
    }

    public BulletObjectManager getBulletObjectManager() {
        return bulletObjectManager;
    }

    public BonusObjectManager getBonusObjectManager() {
        return bonusObjectManager;
    }
}