package game.command;

import engine.action.Action;
import engine.command.EngineCommand;
import engine.object.manager.ObjectManager;
import game.action.CreateBulletAction;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;
import game.object.tank.TankWeapon;

public class TankShootEngineCommand implements EngineCommand {

    private final Tank tank;
    private final GameObjectFactory gameObjectFactory;
    private final ObjectManager<Bullet> bulletObjectManager;

    public TankShootEngineCommand(Tank tank, ObjectManager<Bullet> bulletObjectManager, GameObjectFactory gameObjectFactory) {
        this.tank = tank;
        this.gameObjectFactory = gameObjectFactory;
        this.bulletObjectManager = bulletObjectManager;
    }

    @Override
    public Action getAction() {
        TankWeapon tankWeapon = tank.getTankWeapon();
        return new CreateBulletAction(bulletObjectManager, gameObjectFactory, tankWeapon.getNewBulletBody(), tankWeapon.getBulletSpeed());
    }
}
