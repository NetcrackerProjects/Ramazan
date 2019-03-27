package game.player;

import engine.geometry.Vector;
import engine.object.TokenManager;
import engine.object.manager.ObjectManager;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

public class UserPlayerFactory {

    private final TokenManager tokenManager;
    private final GameObjectFactory gameObjectFactory;

    private final ObjectManager<Tank> tankObjectManager;
    private final ObjectManager<Bullet> bulletObjectManager;

    public UserPlayerFactory(GameObjectFactory gameObjectFactory,
                             ObjectManager<Tank> tankObjectManager, ObjectManager<Bullet> bulletObjectManager) {
        this.tokenManager = new TokenManager();
        this.gameObjectFactory = gameObjectFactory;
        this.tankObjectManager = tankObjectManager;
        this.bulletObjectManager = bulletObjectManager;
    }

    public UserPlayer createPlayer() {
        Tank tank = gameObjectFactory.createTank(new Vector(0, 0), new Vector(1, 1));
        tankObjectManager.addObject(tank);

        return new UserPlayer(tank, bulletObjectManager, tokenManager.nextId());
    }
}
