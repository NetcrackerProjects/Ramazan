package client.connection.data;

import client.sprite.Sprite;
import client.sprite.SpriteFactory;
import engine.geometry.Rectangle;
import engine.geometry.Vector;

class SpriteDecoder {

    private final SpriteFactory spriteFactory;

    SpriteDecoder() {
        this.spriteFactory = new SpriteFactory();
    }

    Sprite loadSprite(String message) {
        String[] splits = message.split(":");

        if (splits.length != 5) {
            throw new IllegalArgumentException();
        }

        int typeId = Integer.parseInt(splits[0]);

        float topLeftX = Float.parseFloat(splits[1]);
        float topLeftY = Float.parseFloat(splits[2]);

        float bottomRightX = Float.parseFloat(splits[3]);
        float bottomRightY = Float.parseFloat(splits[4]);

       Rectangle body = new Rectangle(new Vector(topLeftX, topLeftY), new Vector(bottomRightX, bottomRightY));

        return spriteFactory.createSprite(body, typeId);
    }
}
