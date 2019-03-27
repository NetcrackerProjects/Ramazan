package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.geometry.VectorUtils;
import game.object.Bullet;
import game.object.GameObjectFactory;

public class TankWeapon {

    private final static double RELATIVE_BULLET_SIZE = 0.2;
    private final static double BULLET_MAX_SPEED = 5;

    private final Rectangle tankBody;
    private final Vector tankSpeed;

    private final GameObjectFactory gameObjectFactory;

    TankWeapon(Rectangle tankBody, Vector tankSpeed, GameObjectFactory gameObjectFactory) {
        this.tankBody = tankBody;
        this.tankSpeed = tankSpeed;
        this.gameObjectFactory = gameObjectFactory;
    }

    public Bullet nextBullet() {
        Bullet bullet = gameObjectFactory.createBullet(getNewBulletBody());
        bullet.setSpeed(getBulletSpeed());
        return bullet;
    }

    private Rectangle getNewBulletBody() {
        Vector topLeft = tankBody.getTopLeft();
        Vector bottomRight = tankBody.getBottomRight();

        Vector tankSize = VectorUtils.subtract(bottomRight, topLeft);

        Vector bulletSize = VectorUtils.scale(tankSize, RELATIVE_BULLET_SIZE);

        Vector relativeBulletPos = VectorUtils.scale(tankSize, (1 - RELATIVE_BULLET_SIZE) / 2);

        Vector bulletLeftTop = VectorUtils.sum(topLeft, relativeBulletPos);
        Vector bulletRightBottom = VectorUtils.sum(bulletLeftTop, bulletSize);

        return new Rectangle(bulletLeftTop, bulletRightBottom);
    }

    private Vector getBulletSpeed() {
        return VectorUtils.scale(tankSpeed, BULLET_MAX_SPEED / tankSpeed.norm());
    }
}
