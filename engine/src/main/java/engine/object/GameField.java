package engine.object;

import engine.geometry.Rectangle;
import engine.geometry.Vector;

public class GameField extends GameObject {

    private static final int FIELD_TYPE = 0;

    public GameField(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, id, FIELD_TYPE);
    }

    public boolean covers(Rectangle rectangle) {
        Rectangle body = getBody();
        return body.covers(rectangle);
    }

}
