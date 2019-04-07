package client.connection.control;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class ClientControlTest {

    private ClientControl clientControl;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    @Before
    public void setup() throws IOException {
        this.clientControl = new ClientControl(0);
        this.serverSocket = new ServerSocket(5555);

        clientControl.start("127.0.0.1");

        this.clientSocket = serverSocket.accept();
    }

    @Test
    public void shouldWorkCorrectlyWhenStart() {
        assertNotNull(clientSocket);
    }

    @Test
    public void shouldWorkCorrectlyWhenSendMessage() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ClientControlCommandType type = ClientControlCommandType.MOVE_RIGHT;
        String expected = ClientCommandEncoder.getCommand(type);

        clientControl.sendCommand(type);

        String message = in.readLine();
        assertEquals(expected, message);
    }

    @After
    public void cleanup() throws IOException {
        serverSocket.close();
        clientControl.sendCommand(ClientControlCommandType.EXIT);
    }
}
