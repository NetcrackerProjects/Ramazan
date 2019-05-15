package game.player;

import engine.GameEngine;
import engine.exception.GetFreePositionFailedException;
import engine.geometry.Vector;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import game.exception.FailedCreateUserPlayerException;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

public class UserPlayerFactory {

    private final TokenManager tokenManager;
    private final GameObjectFactory gameObjectFactory;

    private final ObjectManager<Tank> tankObjectManager;
    private final ObjectManager<Bullet> bulletObjectManager;

    private final GameEngine gameEngine;

    public UserPlayerFactory(GameEngine gameEngine, GameObjectFactory gameObjectFactory,
                             ObjectManager<Tank> tankObjectManager, ObjectManager<Bullet> bulletObjectManager) {
        this.tokenManager = new TokenManager();
        this.gameObjectFactory = gameObjectFactory;
        this.tankObjectManager = tankObjectManager;
        this.bulletObjectManager = bulletObjectManager;
        this.gameEngine = gameEngine;
    }

    public UserPlayer createPlayer() throws FailedCreateUserPlayerException {
        try {
            Vector leftTop = gameEngine.getFreePositionForRectangle(Tank.SIZE);
            Tank tank = gameObjectFactory.createTank(leftTop);
            tank.setSpeed(new Vector(1, 0));
            tankObjectManager.addObject(tank);

            UserPlayer userPlayer = new UserPlayer(tank, bulletObjectManager, tokenManager.nextId());

            gameEngine.addPlayer(userPlayer);

            return userPlayer;
        } catch (GetFreePositionFailedException e) {
            throw new FailedCreateUserPlayerException();
        }
    }
}
