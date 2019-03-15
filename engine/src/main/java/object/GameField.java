package object;

import geometry.Rectangle;
import geometry.Vector;

public class GameField extends GameObject {

    private static final int FIELD_TYPE = 0;

    public GameField(Vector leftTop, Vector rightBottom) {
        super(leftTop, rightBottom, GameObject.NO_ID, FIELD_TYPE);
    }

    public boolean covers(Rectangle rectangle) {
        Rectangle body = getBody();
        return body.covers(rectangle);
    }

}
