package client.connection.control;

import client.connection.data.DataConnection;
import commons.ClientPlayerCommandType;
import commons.ClientServerCommandType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ClientControlTest {

    private static final int USER_ID = 0;

    private ClientControl clientControl;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    @Before
    public void setup() throws IOException {
        DataConnection dataConnection = mock(DataConnection.class);
        this.clientControl = new ClientControl(USER_ID, dataConnection);

        this.serverSocket = new ServerSocket(5555);

        clientControl.start("127.0.0.1");

        this.clientSocket = serverSocket.accept();
    }

    @Test
    public void shouldSendValueOfCommandTypeWhenSendCommand() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ClientPlayerCommandType type = ClientPlayerCommandType.MOVE_RIGHT;

        clientControl.sendCommand(type);

        String message = in.readLine();
        assertEquals("p:2", message);
    }

    @After
    public void cleanUp() throws IOException {
        serverSocket.close();
        clientControl.sendCommand(ClientServerCommandType.EXIT);
    }
}
