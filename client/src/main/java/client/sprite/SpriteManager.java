package client.sprite;

import engine.geometry.Vector;

import java.util.Collection;
import java.util.HashSet;

public class SpriteManager {

    private final Collection<Sprite> sprites;

    private Vector monitorPosition;

    public SpriteManager() {
        this.sprites = new HashSet<>();
    }

    public Collection<Sprite> getSprites() {
        return sprites;
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void clean() {
        sprites.clear();
    }

    public void setMonitorPosition(Vector monitorPosition) {
        this.monitorPosition = monitorPosition;
    }

    public Vector getMonitorPosition() {
        return monitorPosition;
    }
}
