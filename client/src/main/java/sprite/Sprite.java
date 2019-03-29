package sprite;

import engine.geometry.Vector;
import engine.object.GameObject;

import java.awt.Image;

public class Sprite {

    private final Vector position;
    private final Image image;

    Sprite(Image image, GameObject gameObject) {
        this.position = gameObject.getPosition();
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }
}