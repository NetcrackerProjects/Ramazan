package server.connection.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.exception.NoSuchUserException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DataSubscriberHandlerTest {

    private static final int PORT = 6666;

    private static final int USER_ID = 1;

    private DataSubscriberHandler dataSubscriberHandler;

    private Socket socket;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    private DataSubscriberManager dataSubscriberManager;

    private PrintWriter printer;

    @Before
    public void setup() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.socket = new Socket("127.0.0.1", PORT);
        this.clientSocket = serverSocket.accept();
        this.printer = new PrintWriter(socket.getOutputStream(), true);
        this.dataSubscriberManager = mock(DataSubscriberManager.class);
        this.dataSubscriberHandler =
                new DataSubscriberHandler(clientSocket, dataSubscriberManager);
        dataSubscriberHandler.start();
    }

    @Test
    public void shouldCallAddDataSubscriberWhenHandleClient() throws InterruptedException, IOException, NoSuchUserException {
        printer.println(USER_ID);

        dataSubscriberHandler.join();
        verify(dataSubscriberManager, times(1)).
                addDataSubscriber(eq(USER_ID), any(Socket.class));
    }

    @After
    public void cleanUp() throws IOException {
        serverSocket.close();
        socket.close();
        clientSocket.close();
    }
}
