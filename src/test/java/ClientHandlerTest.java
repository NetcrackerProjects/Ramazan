import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;


public class ClientHandlerTest {

    private Server server;

    @Before
    public void setup(){
        server = new Server(5555);
        server.start();
    }

    @Test
    public void givenMessage_whenRun_thenCorrect() throws IOException {
        Socket clientSocket = mock(Socket.class);

        ClientHandler clientHandler = new ClientHandler(clientSocket);

        String expected = "test";

        InputStream in = new ByteArrayInputStream(expected.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        when(clientSocket.getInputStream()).thenReturn(in);
        when(clientSocket.getOutputStream()).thenReturn(out);

        clientHandler.run();


        assertEquals(expected, out.toString().trim());
    }

    @Test
    public void givenEndMessage_whenRun_thenCorrect() throws IOException {
        Socket clientSocket = mock(Socket.class);

        ClientHandler clientHandler = new ClientHandler(clientSocket);

        String input = TextMessage.getEndMessage();

        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        when(clientSocket.getInputStream()).thenReturn(in);
        when(clientSocket.getOutputStream()).thenReturn(out);

        clientHandler.run();

        String expected = "bye";

        assertEquals(expected, out.toString().trim());
    }

    @After
    public void tearDown() throws IOException, InterruptedException {
        server.terminate();
    }
}
