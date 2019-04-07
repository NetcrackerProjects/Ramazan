package client.connection;

import client.utils.VectorInt;

import java.io.IOException;

public class UserRegistration {

    private static final int DEFAULT_PORT = 6666;

    private final ClientConnection clientConnection;

    public UserRegistration() {
        this.clientConnection = new ClientConnection();
    }

    public int initializeConnection(String address, VectorInt monitorSize) throws IOException {
        clientConnection.startConnection(address, DEFAULT_PORT);

        clientConnection.sendMessage(formMessage(monitorSize));

        int id = Integer.parseInt(clientConnection.receiveMessage());

        clientConnection.stopConnection();

        return id;
    }

    private String formMessage(VectorInt monitorSize) {
        return monitorSize.getX() + ":" + monitorSize.getY();
    }
}
