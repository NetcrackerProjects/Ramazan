package game;

import engine.geometry.Vector;
import engine.object.manager.ObjectManager;
import game.object.Bonus;
import game.object.GameObjectFactory;

class GameObjectInitializer {

    private final GameObjectFactory gameObjectFactory;

    GameObjectInitializer(GameObjectFactory gameObjectFactory) {
        this.gameObjectFactory = gameObjectFactory;
    }

    void createBonuses(ObjectManager<Bonus> bonusObjectManager) {
        bonusObjectManager.addObject(
                gameObjectFactory.createBonus(new Vector(200, 1)));
    }
}
