package client.connection.data;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameObjectDecoderTest {

    private GameObjectDecoder gameObjectDecoder;
    private String code;

    @Before
    public void setup() {
        this.gameObjectDecoder = new GameObjectDecoder();
        this.code = "1:2:3:4:5";
    }

    @Test
    public void shouldReturnTypeIdWhenDecode() {
        gameObjectDecoder.loadGameObject(code);

        int typeId = gameObjectDecoder.getTypeId();

        assertEquals(1, typeId);
    }

    @Test
    public void shouldReturnObjectBodyWhenDecode() {
        gameObjectDecoder.loadGameObject(code);
        Rectangle expected = new Rectangle(new Vector(2, 3), new Vector(4, 5));

        Rectangle decoded = gameObjectDecoder.getBody();

        assertEquals(expected, decoded);
    }
}
