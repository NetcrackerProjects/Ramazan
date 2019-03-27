package game.action;

import engine.action.Action;
import engine.object.manager.ObjectManager;
import game.object.Bullet;
import game.object.tank.TankWeapon;

public class ShootBulletAction implements Action {

    private final ObjectManager<Bullet> bulletObjectManager;
    private final TankWeapon tankWeapon;

    public ShootBulletAction(TankWeapon tankWeapon, ObjectManager<Bullet> bulletObjectManager) {
        this.bulletObjectManager = bulletObjectManager;
        this.tankWeapon = tankWeapon;
    }

    @Override
    public void doAction() {
        Bullet bullet = tankWeapon.nextBullet();
        bulletObjectManager.addObject(bullet);
    }
}
