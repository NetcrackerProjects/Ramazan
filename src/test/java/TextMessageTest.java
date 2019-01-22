import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TextMessageTest {

    @Test
    public void givenEndSymbol_whenIsEnd_thenTrue(){
        TextMessage textMessage = new TextMessage();
        textMessage.setMessage(".");
        boolean test = textMessage.isEnd();
        boolean expected = true;
        assertEquals(expected, test);
    }
}
