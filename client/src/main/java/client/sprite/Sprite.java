package client.sprite;

import engine.geometry.Vector;

import java.awt.Image;

public class Sprite {

    private final Vector position;
    private final Image image;

    Sprite(Image image, Vector position) {
        this.position = position;
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