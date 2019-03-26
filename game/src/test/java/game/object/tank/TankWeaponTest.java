package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TankWeaponTest {

    private TankWeapon tankWeapon;

    @Before
    public void setup() {
        Rectangle tankBody = new Rectangle(new Vector(2, 4), new Vector(12, 24));
        Vector tankSpeed = new Vector(3, 0);
        this.tankWeapon = new TankWeapon(tankBody, tankSpeed);
    }

    @Test
    public void shouldReturnCorrectRectangleWhenCallForNewBulletBody() {
        Rectangle expected = new Rectangle(new Vector(6, 12), new Vector(8, 16));

        Rectangle bulletBody = tankWeapon.getNewBulletBody();

        assertEquals(expected, bulletBody);
    }

    @Test
    public void shouldWhen() {
        Vector expected = new Vector(5, 0);

        Vector bulletSpeed = tankWeapon.getBulletSpeed();

        assertEquals(expected, bulletSpeed);
    }
}
