package client.connection.control;

import client.connection.ClientConnection;
import client.connection.visual.VisualConnection;
import commons.ClientControlCommandType;

import java.io.IOException;

public class ClientControl {

    private static final int DEFAULT_PORT = 5555;

    private final ClientConnection clientConnection;

    private final VisualConnection visualConnection;

    private final int clientId;

    public ClientControl(int clientId, VisualConnection visualConnection) {
        this.clientConnection = new ClientConnection();
        this.clientId = clientId;
        this.visualConnection = visualConnection;
    }

    public void start(String address) throws IOException {
        clientConnection.startConnection(address, DEFAULT_PORT);
    }

    public void sendCommand(ClientControlCommandType clientControlCommandType) {
        clientConnection.sendMessage(createMessage(clientControlCommandType));

        if (clientControlCommandType == ClientControlCommandType.EXIT) {
            stop();
        }
    }

    private String createMessage(ClientControlCommandType clientControlCommandType) {
        StringBuilder message = new StringBuilder();

        message.append(ClientControlCommandType.valueOf(clientControlCommandType));

        if (clientControlCommandType == ClientControlCommandType.START) {
            message.append(":");
            message.append(clientId);
        }

        return message.toString();
    }

    private void stop() {
        try {
            clientConnection.stopConnection();
            visualConnection.terminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
