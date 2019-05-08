package server.connection.data;

import engine.geometry.Rectangle;
import engine.geometry.Vector;
import engine.object.GameObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameObjectEncoderTest {

    private static final int TYPE_ID = 1;
    private static final Rectangle OBJECT_BODY = new Rectangle(new Vector(2, 3), new Vector(4, 5));

    @Test
    public void shouldReturnTypeIdAndBodyPointsWhenCalledEncode() {
        GameObject gameObject = mock(GameObject.class);
        when(gameObject.getTypeId()).thenReturn(TYPE_ID);
        when(gameObject.getBody()).thenReturn(OBJECT_BODY);

        String encoded = GameObjectEncoder.encode(gameObject);

        assertEquals("1:2.0:3.0:4.0:5.0", encoded);
    }
}
