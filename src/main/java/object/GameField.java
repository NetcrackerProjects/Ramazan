package object;

import geometry.Rectangle;
import geometry.Vector;

public class GameField extends GameObject {

    GameField(Vector leftTop, Vector rightBottom, int id) {
        super(leftTop, rightBottom, id, Type.FIELD);
    }

    public boolean covers(Rectangle rectangle) {
        Rectangle body = getBody();
        return body.covers(rectangle);
    }

}
