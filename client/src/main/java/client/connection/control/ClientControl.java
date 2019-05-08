package client.connection.control;

import client.connection.ClientConnection;
import client.connection.data.DataConnection;
import commons.ClientPlayerCommandType;
import commons.ClientServerCommandType;

import java.io.IOException;

public class ClientControl {

    private static final int DEFAULT_PORT = 5555;

    private final ClientConnection clientConnection;

    private final DataConnection dataConnection;

    private final int clientId;

    public ClientControl(int clientId, DataConnection dataConnection) {
        this.clientConnection = new ClientConnection();
        this.clientId = clientId;
        this.dataConnection = dataConnection;
    }

    public void start(String address) throws IOException {
        clientConnection.startConnection(address, DEFAULT_PORT);
    }

    void sendCommand(ClientPlayerCommandType clientPlayerCommandType) {
        clientConnection.sendMessage(createMessage(clientPlayerCommandType));
    }

    public void sendCommand(ClientServerCommandType clientServerCommandType) {
        clientConnection.sendMessage(createMessage(clientServerCommandType));

        if (clientServerCommandType == ClientServerCommandType.EXIT) {
            stop();
        }
    }

    private String createMessage(ClientPlayerCommandType clientPlayerCommandType) {
        return "p:" + ClientPlayerCommandType.valueOf(clientPlayerCommandType);
    }

    private String createMessage(ClientServerCommandType clientServerCommandType) {
        StringBuilder message = new StringBuilder();

        message.append("s:");

        message.append(ClientServerCommandType.valueOf(clientServerCommandType));

        if (clientServerCommandType == ClientServerCommandType.START) {
            message.append(":");
            message.append(clientId);
        }

        return message.toString();
    }

    private void stop() {
        try {
            clientConnection.stopConnection();
            dataConnection.terminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
