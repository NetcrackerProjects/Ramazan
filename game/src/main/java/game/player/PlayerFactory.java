package game.player;

import engine.geometry.Vector;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import game.object.GameObjectFactory;
import game.object.Tank;

public class PlayerFactory {

    private final TokenManager tokenManager;
    private final GameObjectFactory gameObjectFactory;
    private final ObjectManager<Tank> tankObjectManager;

    public PlayerFactory(GameObjectFactory gameObjectFactory, ObjectManager<Tank> tankObjectManager) {
        this.tokenManager = new TokenManager();
        this.gameObjectFactory = gameObjectFactory;
        this.tankObjectManager = tankObjectManager;
    }

    public Player createPlayer() {
        Tank tank = gameObjectFactory.createTank(new Vector(0, 0), new Vector(1, 1));
        tankObjectManager.addObject(tank);

        return new Player(tank, tokenManager.nextId());
    }
}
