package client.connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class ClientConnectionTest {

    private ClientConnection clientConnection;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    @Before
    public void setup() throws IOException {
        this.serverSocket = new ServerSocket(5555);
        this.clientConnection = new ClientConnection();
        clientConnection.startConnection("127.0.0.1", 5555);
        this.clientSocket = serverSocket.accept();
    }

    @Test
    public void shouldWorkCorrectlyWhenSendMessage() throws IOException {
        String expected = "test";
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        clientConnection.sendMessage(expected);

        String message = in.readLine();
        assertEquals(expected, message);
    }

    @Test
    public void shouldWorkCorrectlyWhenReceiveMessage() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        out.println("test");

        String message = clientConnection.receiveMessage();

        assertEquals("test", message);
    }

    @After
    public void cleanUp() throws IOException {
        serverSocket.close();
        clientConnection.stopConnection();
    }
}
