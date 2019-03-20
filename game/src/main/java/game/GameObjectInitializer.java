package game;

import engine.geometry.Vector;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import game.object.Bonus;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.Tank;

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

    Tank createPlayer(ObjectManager<Tank> tankObjectManager) {
        Tank tank = gameObjectFactory.createTank(new Vector(9, 0), new Vector(10, 1));
        tankObjectManager.addObject(tank);
        return tank;
    }
}
