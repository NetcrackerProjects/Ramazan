package client.sprite;

import game.object.Type;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

class ImageMap {

    private final Map<Integer, Image> imageMap;

    ImageMap() {
        this.imageMap = new HashMap<>();
        ImageIcon imageIcon = new ImageIcon("client/src/main/resources/test.png");
        imageMap.put(Type.TANK, imageIcon.getImage());
    }

    Image getImage(int typeId) {
        Image image = imageMap.get(typeId);

        if (image == null) {
            throw new IllegalArgumentException();
        }

        return image;
    }
}
