package client.sprite;

import engine.geometry.Rectangle;
import engine.geometry.Vector;

import java.awt.Image;

public class Sprite {

    private final Vector leftTop;
    private final Vector rightBottom;
    private final Image image;

    Sprite(Image image, Rectangle body) {
        this.leftTop = body.getTopLeft();
        this.rightBottom = body.getBottomRight();
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Vector getLeftTop() {
        return leftTop;
    }

    public Vector getRightBottom() {
        return rightBottom;
    }
}