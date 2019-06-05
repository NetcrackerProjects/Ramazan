package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.geometry.VectorUtils;

public class TankWeapon {

    private final static double RELATIVE_BULLET_SIZE = 0.2;
    private final static double BULLET_MAX_SPEED = 5;

    private final static Vector INITIAL_DIRECTION = new Vector(1, 0);

    private final Rectangle tankBody;

    private final int tankId;

    private Vector direction;

    TankWeapon(Rectangle tankBody, int tankId) {
        this.tankBody = tankBody;
        this.direction = new Vector(INITIAL_DIRECTION);
        this.tankId = tankId;
    }

    void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Rectangle getNewBulletBody() {
        Vector topLeft = tankBody.getTopLeft();
        Vector bottomRight = tankBody.getBottomRight();

        Vector tankSize = VectorUtils.subtract(bottomRight, topLeft);

        Vector bulletSize = VectorUtils.scale(tankSize, RELATIVE_BULLET_SIZE);

        Vector relativeBulletPos = VectorUtils.scale(tankSize, (1 - RELATIVE_BULLET_SIZE) / 2);

        Vector bulletLeftTop = VectorUtils.sum(topLeft, relativeBulletPos);
        Vector bulletRightBottom = VectorUtils.sum(bulletLeftTop, bulletSize);

        return new Rectangle(bulletLeftTop, bulletRightBottom);
    }

    public Vector getBulletSpeed() {
        return VectorUtils.scale(direction, BULLET_MAX_SPEED);
    }

    public int getTankId() {
        return tankId;
    }
}
