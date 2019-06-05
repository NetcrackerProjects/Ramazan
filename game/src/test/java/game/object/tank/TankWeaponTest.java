package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TankWeaponTest {

    private static final int TANK_ID = 1;

    private TankWeapon tankWeapon;

    @Before
    public void setup() {
        Rectangle tankBody = new Rectangle(new Vector(2, 4), new Vector(12, 24));
        this.tankWeapon = new TankWeapon(tankBody, TANK_ID);
    }

    @Test
    public void shouldSetCorrectBulletBodyWhenNextBullet() {
        Rectangle expected = new Rectangle(new Vector(6, 12), new Vector(8, 16));

        Rectangle body = tankWeapon.getNewBulletBody();

        assertEquals(expected, body);
    }

    @Test
    public void shouldReturnCorrectWhenGetBulletSpeed() {
        Vector expected = new Vector(5, 0);

        Vector speed = tankWeapon.getBulletSpeed();

        assertEquals(expected, speed);
    }
}
