package client.connection;

import java.io.IOException;

public class ClientRegistration {

    private static final String NAME = "TEST_NAME";

    private static final int DEFAULT_PORT = 6666;

    private final ClientConnection clientConnection;

    public ClientRegistration() {
        this.clientConnection = new ClientConnection();
    }

    public int registerClient(String address) throws IOException {
        clientConnection.startConnection(address, DEFAULT_PORT);

        clientConnection.sendMessage(NAME);

        int id = Integer.parseInt(clientConnection.receiveMessage());

        clientConnection.stopConnection();

        return id;
    }
}
