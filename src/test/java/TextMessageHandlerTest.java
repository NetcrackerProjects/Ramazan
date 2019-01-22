import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TextMessageHandlerTest {

    @Test
    public void givenText_whenGetMessage_thenSameMessage(){
        MessageHandler messageHandler = new TextMessageHandler();
        String expected = "test";
        Message message = messageHandler.getMessage(expected);
        assertEquals(expected, message.getTextMessage());
    }
}
