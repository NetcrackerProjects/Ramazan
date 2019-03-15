package game;

import engine.geometry.Vector;
import game.object.Tank;
import game.object.Bullet;
import game.object.Bonus;
import engine.object.TokenManager;
import game.object.GameObjectFactory;
import engine.object.manager.ObjectManager;

class GameObjectInitializer {

    private final GameObjectFactory gameObjectFactory;

    GameObjectInitializer(TokenManager tokenManager) {
        this.gameObjectFactory = new GameObjectFactory(tokenManager);
    }

    void createTanks(ObjectManager<Tank> tankObjectManager) {
        tankObjectManager.addObject(
                gameObjectFactory.createTank(new Vector(1, 1), new Vector(2, 2)));
    }

    void createBullets(ObjectManager<Bullet> bulletObjectManager) {
        bulletObjectManager.addObject(
                gameObjectFactory.createBullet(new Vector(3, 3), new Vector(4, 4)));
    }

    void createBonuses(ObjectManager<Bonus> bonusObjectManager) {
        bonusObjectManager.addObject(
                gameObjectFactory.createBonus(new Vector(5, 5), new Vector(6, 6)));
    }
}
