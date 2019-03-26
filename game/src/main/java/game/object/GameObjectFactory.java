package game.object;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.object.TokenManager;
import game.object.tank.Tank;

public class GameObjectFactory {

    private final TokenManager tokenManager;

    public GameObjectFactory(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public Tank createTank(Vector leftTop, Vector rightBottom) {
        return new Tank(leftTop, rightBottom, tokenManager.nextId());
    }

    public Bullet createBullet(Vector leftTop, Vector rightBottom) {
        return new Bullet(leftTop, rightBottom, 1, tokenManager.nextId());
    }

    public Bullet createBullet(Rectangle body) {
        return new Bullet(body, 1, tokenManager.nextId());
    }

    public Bonus createBonus(Vector leftTop, Vector rightBottom) {
        return new Bonus(leftTop, rightBottom, 5, tokenManager.nextId());
    }
}
