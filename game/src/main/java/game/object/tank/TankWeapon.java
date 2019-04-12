package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.geometry.VectorUtils;
import game.object.Bullet;
import game.object.GameObjectFactory;

public class TankWeapon {

    private final static double RELATIVE_BULLET_SIZE = 0.2;
    private final static double BULLET_MAX_SPEED = 5;

    private final static Vector INITIAL_DIRECTION = new Vector(1, 0);

    private final Rectangle tankBody;

    private final GameObjectFactory gameObjectFactory;

    private final int tankId;

    private Vector direction;

    TankWeapon(Rectangle tankBody, int tankId, GameObjectFactory gameObjectFactory) {
        this.tankBody = tankBody;
        this.direction = new Vector(INITIAL_DIRECTION);
        this.tankId = tankId;
        this.gameObjectFactory = gameObjectFactory;
    }

    public Bullet nextBullet() {
        Bullet bullet = gameObjectFactory.createBullet(getNewBulletBody(), tankId);
        bullet.setSpeed(getBulletSpeed());
        return bullet;
    }

    void setDirection(Vector direction) {
        this.direction = direction;
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
        return VectorUtils.scale(direction, BULLET_MAX_SPEED);
    }
}
