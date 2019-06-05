package game.player;

import engine.GameEngine;
import engine.exception.GetFreePositionFailedException;
import engine.geometry.Vector;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import game.exception.FailedCreateUserException;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

public class UserFactory {

    private final TokenManager tokenManager;
    private final GameObjectFactory gameObjectFactory;

    private final ObjectManager<Tank> tankObjectManager;

    private final GameEngine gameEngine;

    public UserFactory(GameEngine gameEngine, GameObjectFactory gameObjectFactory,
                       ObjectManager<Tank> tankObjectManager) {
        this.tokenManager = new TokenManager();
        this.gameObjectFactory = gameObjectFactory;
        this.tankObjectManager = tankObjectManager;
        this.gameEngine = gameEngine;
    }

    public User createUser(String name) throws FailedCreateUserException {
        try {
            Vector leftTop = gameEngine.getFreePositionForRectangle(Tank.SIZE);
            Tank tank = gameObjectFactory.createTank(leftTop);
            tank.setSpeed(new Vector(1, 0));
            tankObjectManager.addObject(tank);

            User user = new User(tokenManager.nextId(), tank.getId(), name);

            gameEngine.addPlayer(user);

            return user;
        } catch (GetFreePositionFailedException e) {
            throw new FailedCreateUserException();
        }
    }
}
