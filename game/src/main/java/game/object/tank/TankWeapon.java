package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;

public class TankWeapon {

    private final static double RELATIVE_BULLET_SIZE = 0.2;
    private final static double BULLET_MAX_SPEED = 5;

    private final Rectangle tankBody;
    private final Vector tankSpeed;

    TankWeapon(Rectangle tankBody, Vector tankSpeed) {
        this.tankBody = tankBody;
        this.tankSpeed = tankSpeed;
    }

    public Rectangle getNewBulletBody() {
        Vector topLeft = tankBody.getTopLeft();
        Vector bottomRight = tankBody.getBottomRight();

        Vector tankSize = Vector.subtract(bottomRight, topLeft);

        Vector bulletSize = Vector.scale(tankSize, RELATIVE_BULLET_SIZE);

        Vector relativeBulletPos = Vector.scale(tankSize, (1 - RELATIVE_BULLET_SIZE) / 2);

        Vector bulletLeftTop = Vector.sum(topLeft, relativeBulletPos);
        Vector bulletRightBottom = Vector.sum(bulletLeftTop, bulletSize);

        return new Rectangle(bulletLeftTop, bulletRightBottom);
    }

    public Vector getBulletSpeed() {
        return Vector.scale(tankSpeed, BULLET_MAX_SPEED / tankSpeed.absolute());
    }
}
