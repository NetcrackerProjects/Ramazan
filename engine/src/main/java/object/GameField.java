package object;

import geometry.Rectangle;
import geometry.Vector;

public class GameField extends GameObject {

    public GameField(Vector leftTop, Vector rightBottom) {
        super(leftTop, rightBottom, GameObject.NO_ID, Type.FIELD);
    }

    public boolean covers(Rectangle rectangle) {
        Rectangle body = getBody();
        return body.covers(rectangle);
    }

}
