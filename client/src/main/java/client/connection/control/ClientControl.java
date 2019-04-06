package client.connection.control;

import client.connection.ClientConnection;

import java.io.IOException;

public class ClientControl {

    private static final int DEFAULT_PORT = 5555;

    private final ClientConnection clientConnection;

    private final int userId;

    public ClientControl(int userId) {
        this.clientConnection = new ClientConnection();
        this.userId = userId;
    }

    public void start(String address) throws IOException {
        clientConnection.startConnection(address, DEFAULT_PORT);
    }

    void sendCommand(ClientControlCommandType clientControlCommandType) {
        StringBuilder message = new StringBuilder();
        message.append(formMessage(clientControlCommandType));

        if (clientControlCommandType == ClientControlCommandType.START) {
            message.append(":");
            message.append(userId);
        }

        clientConnection.sendMessage(message.toString());

        if (clientControlCommandType == ClientControlCommandType.EXIT) {
            stop();
        }
    }

    private String formMessage(ClientControlCommandType clientControlCommandType) {
        StringBuilder message = new StringBuilder();

        message.append(ClientCommandEncoder.getCommand(clientControlCommandType));

        if (clientControlCommandType == ClientControlCommandType.START) {
            message.append(":");
            message.append(userId);
        }

        return message.toString();
    }

    private void stop() {
        try {
            clientConnection.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
