package game.object.tank;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import game.object.Bullet;
import game.object.GameObjectFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

public class TankWeaponTest {

    private TankWeapon tankWeapon;
    private GameObjectFactory gameObjectFactory;
    private Bullet bullet;

    @Before
    public void setup() {
        this.gameObjectFactory = mock(GameObjectFactory.class);
        this.bullet = mock(Bullet.class);
        when(gameObjectFactory.createBullet(any(Rectangle.class))).thenReturn(bullet);

        Rectangle tankBody = new Rectangle(new Vector(2, 4), new Vector(12, 24));
        Vector tankSpeed = new Vector(3, 0);
        this.tankWeapon = new TankWeapon(tankBody, tankSpeed, gameObjectFactory);
    }

    @Test
    public void shouldReturnBulletWhenNextBullet() {
        Bullet result = tankWeapon.nextBullet();

        assertEquals(bullet, result);
    }

    @Test
    public void shouldSetCorrectBulletBodyWhenNextBullet() {
        Rectangle expected = new Rectangle(new Vector(6, 12), new Vector(8, 16));

        tankWeapon.nextBullet();

        verify(gameObjectFactory, times(1)).createBullet(expected);
    }

    @Test
    public void shouldReturnCorrectWhenGetBulletSpeed() {
        Vector expected = new Vector(5, 0);

        tankWeapon.nextBullet();

        verify(bullet, times(1)).setSpeed(expected);
    }
}
