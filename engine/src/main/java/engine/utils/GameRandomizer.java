package engine.utils;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.geometry.VectorUtils;
import engine.object.GameField;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameRandomizer {

    private static final int PRECESSION = 1000;

    public static Vector getRandomVectorAtGameField(GameField gameField) {
        Random random = ThreadLocalRandom.current();
        Rectangle body = gameField.getBody();
        Vector size = VectorUtils.subtract(body.getBottomRight(), body.getTopLeft());

        double x = (double)random.nextInt(PRECESSION)/ PRECESSION * size.getX() + body.getTopLeft().getX();
        double y = (double)random.nextInt(PRECESSION)/ PRECESSION * size.getY() + body.getTopLeft().getY();

        return new Vector(x, y);
    }
}
