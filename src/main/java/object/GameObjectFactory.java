package object;

import geometry.Vector;

public class GameObjectFactory {

    private static int currentId = 0;

    public static Tank createTank(Vector leftTop, Vector rightBottom) {
        return new Tank(leftTop, rightBottom, getNewId());
    }

    public static Bullet createBullet(Vector leftTop, Vector rightBottom) {
        return new Bullet(leftTop, rightBottom, 1, getNewId());
    }

    public static Bonus createBonus(Vector leftTop, Vector rightBottom) {
        return new Bonus(leftTop, rightBottom, 5, getNewId());
    }

    public static GameField createGameField(Vector leftTop, Vector rightBottom) {
        return new GameField(leftTop, rightBottom, getNewId());
    }

    private static int getNewId() {
        return currentId++;
    }
}
