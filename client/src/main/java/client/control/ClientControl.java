package client.control;

import java.io.IOException;

public class ClientControl {

    private final ClientConnection clientConnection;

    public ClientControl() throws IOException {
        this.clientConnection = new ClientConnection();
        start();
    }

    void sendCommand(ClientControlCommandType clientControlCommandType) {
        String command = formMessage(clientControlCommandType);

        clientConnection.sendCommand(command);

        if (clientControlCommandType == ClientControlCommandType.EXIT) {
            stop();
        }
    }

    private String formMessage(ClientControlCommandType clientControlCommandType) {
        return ClientCommandsEncoder.getCommand(clientControlCommandType);
    }

    private void start() throws IOException {
        clientConnection.startConnection();
    }

    private void stop() {
        try {
            clientConnection.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
