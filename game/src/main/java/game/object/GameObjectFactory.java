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

    public Tank createTank(Vector leftTop) {
        return new Tank(leftTop, this, tokenManager.nextId());
    }

    public Bullet createBullet(Rectangle body, int tankId) {
        return new Bullet(body, 1, tokenManager.nextId(), tankId);
    }

    public Bonus createBonus(Vector leftTop) {
        return new Bonus(leftTop, 5, tokenManager.nextId());
    }
}
