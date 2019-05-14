package client.connection.data;

import client.sprite.Sprite;
import engine.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpriteDecoderTest {

    private SpriteDecoder spriteDecoder;
    private String code;

    @Before
    public void setup() {
        this.spriteDecoder = new SpriteDecoder();
        this.code = "1:2:3:4:5";
    }

    @Test
    public void shouldDecodeLeftTopPositionCorrectly() {
        spriteDecoder.loadSprite(code);
        Vector expected = new Vector(2, 3);

        Sprite sprite = spriteDecoder.loadSprite(code);

        assertEquals(expected, sprite.getLeftTop());
    }

    @Test
    public void shouldDecodeRightBottomPositionCorrectly() {
        spriteDecoder.loadSprite(code);
        Vector expected = new Vector(4, 5);

        Sprite sprite = spriteDecoder.loadSprite(code);

        assertEquals(expected, sprite.getRightBottom());
    }
}
