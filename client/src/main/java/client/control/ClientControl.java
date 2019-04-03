package client.control;

import java.io.IOException;

public class ClientControl {

    private final ClientConnection clientConnection;

    public ClientControl() {
        this.clientConnection = new ClientConnection();
    }

    public void start() throws IOException {
        clientConnection.startConnection();
    }

    void sendCommand(ClientControlCommandType clientControlCommandType) {
        String command = formMessage(clientControlCommandType);

        clientConnection.sendCommand(command);

        if (clientControlCommandType == ClientControlCommandType.EXIT) {
            stop();
        }
    }

    private String formMessage(ClientControlCommandType clientControlCommandType) {
        return ClientCommandEncoder.getCommand(clientControlCommandType);
    }

    private void stop() {
        try {
            clientConnection.stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
