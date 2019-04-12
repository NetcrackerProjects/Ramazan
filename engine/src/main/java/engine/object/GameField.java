package engine.object;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.geometry.VectorUtils;

import java.util.Random;

public class GameField extends GameObject {

    public static final double frictionCoefficient = 0.3;

    private static final int FIELD_TYPE = 0;

    private static final int PRECESSION = 1000;

    private final Random random;

    public GameField(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, false, id, FIELD_TYPE);
        this.random = new Random();
    }

    public boolean covers(Rectangle rectangle) {
        Rectangle body = getBody();
        return body.covers(rectangle);
    }

    public Vector getRandomPoint() {
        Vector size = VectorUtils.subtract(getBody().getBottomRight(), getBody().getTopLeft());

        double x = (double)random.nextInt(PRECESSION)/ PRECESSION * size.getX() + getBody().getTopLeft().getX();
        double y = (double)random.nextInt(PRECESSION)/ PRECESSION * size.getY() + getBody().getTopLeft().getY();

        return new Vector(x, y);
    }
}
