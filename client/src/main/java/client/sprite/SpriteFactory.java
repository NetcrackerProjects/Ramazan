package client.sprite;

import engine.object.GameObject;

public class SpriteFactory {

    private final ImageMap imageMap;

    public SpriteFactory() {
        this.imageMap = new ImageMap();
    }

    public Sprite createSprite(GameObject gameObject) {
        return new Sprite(imageMap.getImage(gameObject.getTypeId()), gameObject.getPosition());
    }
}
