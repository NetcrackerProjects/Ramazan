package client.connection;

import client.utils.VectorInt;

import java.io.IOException;

public class ClientRegistration {

    private static final int DEFAULT_PORT = 6666;

    private final ClientConnection clientConnection;

    public ClientRegistration() {
        this.clientConnection = new ClientConnection();
    }

    public int registerClient(String address, VectorInt monitorSize) throws IOException {
        clientConnection.startConnection(address, DEFAULT_PORT);

        clientConnection.sendMessage(monitorSize.getX() + ":" + monitorSize.getY());

        int id = Integer.parseInt(clientConnection.receiveMessage());

        clientConnection.stopConnection();

        return id;
    }
}
