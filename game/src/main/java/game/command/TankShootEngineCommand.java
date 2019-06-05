package game.command;

import engine.action.Action;
import engine.command.EngineCommand;
import engine.object.manager.ObjectManager;
import game.action.ShootBulletAction;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;
import game.object.tank.TankWeapon;

public class TankShootEngineCommand implements EngineCommand {

    private final Tank tank;
    private final ObjectManager<Bullet> bulletObjectManager;
    private final GameObjectFactory gameObjectFactory;

    public TankShootEngineCommand(Tank tank, ObjectManager<Bullet> bulletObjectManager, GameObjectFactory gameObjectFactory) {
        this.tank = tank;
        this.bulletObjectManager = bulletObjectManager;
        this.gameObjectFactory = gameObjectFactory;
    }

    @Override
    public Action getAction() {
        TankWeapon tankWeapon = tank.getTankWeapon();
        return new ShootBulletAction(tankWeapon, bulletObjectManager, gameObjectFactory);
    }
}
