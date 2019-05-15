package server.connection.data;

import engine.geometry.Rectangle;
import engine.object.GameObject;

class GameObjectEncoder {

    static String encode(GameObject gameObject) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gameObject.getTypeId());
        stringBuilder.append(":");

        Rectangle body = gameObject.getBody();

        stringBuilder.append(body.getTopLeft().getX());
        stringBuilder.append(":");

        stringBuilder.append(body.getTopLeft().getY());
        stringBuilder.append(":");

        stringBuilder.append(body.getBottomRight().getX());
        stringBuilder.append(":");

        stringBuilder.append(body.getBottomRight().getY());

        return stringBuilder.toString();
    }

}
