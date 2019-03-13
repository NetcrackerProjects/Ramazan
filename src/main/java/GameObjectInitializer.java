import geometry.Vector;
import object.Bonus;
import object.Bullet;
import object.GameObjectFactory;
import object.Tank;
import object.manager.ObjectManager;

class GameObjectInitializer {

    void createTanks(ObjectManager<Tank> tankObjectManager) {
        tankObjectManager.addObject(GameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2)));
    }

    void createBullets(ObjectManager<Bullet> bulletObjectManager) {
        bulletObjectManager.addObject(GameObjectFactory.createBullet(new Vector(3, 3), new Vector(4, 4)));
    }

    void createBonuses(ObjectManager<Bonus> bonusObjectManager) {
        bonusObjectManager.addObject(GameObjectFactory.createBonus(new Vector(5, 5), new Vector(6, 6)));
    }
}