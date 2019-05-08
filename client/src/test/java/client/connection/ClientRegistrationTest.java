package client.connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientRegistrationTest {

    private ClientRegistration clientRegistration;
    private ServerSocket serverSocket;

    @Before
    public void setup() throws IOException {
        this.clientRegistration = new ClientRegistration();
        this.serverSocket = new ServerSocket(6666);
    }

    @Test
    public void shouldFetchIdWhenTryingToRegisterClient() throws IOException {
        Thread server = new Thread(() -> {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println(10);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start();

        int fetchedId = clientRegistration.registerClient("127.0.0.1");

        assertEquals(10, fetchedId);
    }

    @After
    public void cleanUp() throws IOException {
        serverSocket.close();
    }
}
