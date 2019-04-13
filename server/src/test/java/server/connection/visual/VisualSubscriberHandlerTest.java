package server.connection.visual;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class VisualSubscriberHandlerTest {

    private static final int PORT = 6666;

    private static final int USER_ID = 1;

    private VisualSubscriberHandler visualSubscriberHandler;

    private Socket socket;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private VisualSubscriberManager visualSubscriberManager;

    private PrintWriter printer;

    @Before
    public void setup() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.socket = new Socket("127.0.0.1", PORT);
        this.clientSocket = serverSocket.accept();
        this.printer = new PrintWriter(socket.getOutputStream(), true);
        this.visualSubscriberManager = mock(VisualSubscriberManager.class);
        this.visualSubscriberHandler =
                new VisualSubscriberHandler(clientSocket, visualSubscriberManager);
        visualSubscriberHandler.start();
    }

    @Test
    public void shouldCallAddVisualSubscriberWhenHandleClient() throws InterruptedException, IOException {
        printer.println(USER_ID);

        visualSubscriberHandler.join();
        verify(visualSubscriberManager, times(1)).
                addVisualSubscriber(eq(USER_ID), any(Socket.class));
    }

    @After
    public void cleanUp() throws IOException {
        serverSocket.close();
        socket.close();
        clientSocket.close();
    }
}
