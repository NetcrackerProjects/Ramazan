package game.action;

import engine.action.Action;
import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.object.manager.ObjectManager;
import game.object.Bullet;
import game.object.GameObjectFactory;

public class CreateBulletAction implements Action {

    private final ObjectManager<Bullet> bulletObjectManager;
    private final GameObjectFactory gameObjectFactory;
    private final Rectangle bulletBody;
    private final Vector speed;

    public CreateBulletAction(ObjectManager<Bullet> bulletObjectManager, GameObjectFactory gameObjectFactory,
                              Rectangle bulletBody, Vector speed) {
        this.bulletObjectManager = bulletObjectManager;
        this.gameObjectFactory = gameObjectFactory;
        this.bulletBody = bulletBody;
        this.speed = speed;
    }

    @Override
    public void doAction() {
        Bullet bullet = gameObjectFactory.createBullet(bulletBody);
        bullet.setSpeed(speed);
        bulletObjectManager.addObject(bullet);
    }
}
