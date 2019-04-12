package client.sprite;

import engine.geometry.Rectangle;

public class SpriteFactory {

    private final ImageMap imageMap;

    public SpriteFactory() {
        this.imageMap = new ImageMap();
    }

    public Sprite createSprite(Rectangle body, int typeId) {
        return new Sprite(imageMap.getImage(typeId), body);
    }
}
