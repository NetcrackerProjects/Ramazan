package game.action;

import engine.action.Action;
import engine.object.manager.ObjectManager;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.TankWeapon;

public class ShootBulletAction implements Action {

    private final ObjectManager<Bullet> bulletObjectManager;
    private final TankWeapon tankWeapon;
    private final GameObjectFactory gameObjectFactory;

    public ShootBulletAction(TankWeapon tankWeapon, ObjectManager<Bullet> bulletObjectManager,
                             GameObjectFactory gameObjectFactory) {
        this.bulletObjectManager = bulletObjectManager;
        this.tankWeapon = tankWeapon;
        this.gameObjectFactory = gameObjectFactory;
    }

    @Override
    public void doAction() {
        Bullet bullet = gameObjectFactory.createBullet(tankWeapon.getNewBulletBody(), tankWeapon.getTankId());
        bullet.setSpeed(tankWeapon.getBulletSpeed());

        bulletObjectManager.addObject(bullet);
    }
}
