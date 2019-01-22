import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class ClientTest {

    private Server server;

    @Before
    public void setup(){
        ServerStarter starter = new ServerStarter();
        server = starter.start(5555);
    }

    @Test(expected = IOException.class)
    public void givenNoServer_whenStartConnection_thenExceptionThrown() throws IOException {
        Client client = new Client();
        client.startConnection("127.0.0.1", 0);
    }

    @Test
    public void givenClient1_whenSendMessage_thenCorrect() throws IOException {
        Client client1 = new Client();
        client1.startConnection("127.0.0.1", 5555);
        TextMessage mes = new TextMessage();
        mes.setMessage("hello");
        String msg1 = client1.sendMessage(mes);
        mes.setMessage("world");
        String msg2 = client1.sendMessage(mes);
        mes.setMessage(".");
        String terminate = client1.sendMessage(mes);

        assertEquals(msg1, "hello");
        assertEquals(msg2, "world");
        assertEquals(terminate, "bye");
    }

    @After
    public void teardown() throws InterruptedException, IOException {
        server.terminate();
    }

}
