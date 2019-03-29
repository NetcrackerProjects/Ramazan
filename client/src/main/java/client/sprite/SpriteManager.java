package client.sprite;

import java.util.Collection;
import java.util.HashSet;

public class SpriteManager {

    private final Collection<Sprite> sprites;

    public SpriteManager() {
        this.sprites = new HashSet<>();
    }

    public Collection<Sprite> getSprites() {
        return sprites;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}
