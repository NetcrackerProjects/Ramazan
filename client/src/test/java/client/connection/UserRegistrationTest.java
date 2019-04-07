package client.connection;

import client.connection.UserRegistration;
import client.utils.VectorInt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class UserRegistrationTest {

    private UserRegistration userRegistration;
    private ServerSocket serverSocket;
    private Thread server;

    @Before
    public void setup() throws IOException {
        this.userRegistration = new UserRegistration();
        this.serverSocket = new ServerSocket(6666);
    }

    @Test
    public void shouldWorkCorrectlyWhenSendMessage() throws IOException {
        VectorInt message = new VectorInt(10, 10);
        AtomicReference<String> receivedMessage = new AtomicReference<>();
        this.server = new Thread(() -> {
            try {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                receivedMessage.set(in.readLine());

                out.println(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start();

        userRegistration.initializeConnection("127.0.0.1", message);

        assertEquals("10:10", receivedMessage.get());
    }

    @Test
    public void shouldWorkCorrectlyWhenReceiveMessage() throws IOException {
        int expected = 10;
        VectorInt message = new VectorInt(10, 10);
        this.server = new Thread(() -> {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println(expected);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        server.start();

        int id = userRegistration.initializeConnection("127.0.0.1", message);
        assertEquals(expected, id);
    }

    @After
    public void cleanup() throws IOException {
        serverSocket.close();
    }
}
