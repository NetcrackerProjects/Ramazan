package client.connection.data;

import engine.geometry.Rectangle;
import engine.geometry.Vector;

class GameObjectDecoder {

    private int typeId;
    private Rectangle body;

    void loadGameObject(String message) {
        String[] splits = message.split(":");

        if (splits.length != 5) {
            throw new IllegalArgumentException();
        }

        this.typeId = Integer.parseInt(splits[0]);

        float topLeftX = Float.parseFloat(splits[1]);
        float topLeftY = Float.parseFloat(splits[2]);

        float bottomRightX = Float.parseFloat(splits[3]);
        float bottomRightY = Float.parseFloat(splits[4]);

        this.body = new Rectangle(new Vector(topLeftX, topLeftY), new Vector(bottomRightX, bottomRightY));
    }

    int getTypeId() {
        return typeId;
    }

    Rectangle getBody() {
        return body;
    }
}
