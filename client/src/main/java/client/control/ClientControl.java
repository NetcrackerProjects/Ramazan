package client.control;

import java.io.IOException;

public class ClientControl {

    private final ClientConnection clientConnection;

    public ClientControl() throws IOException {
        this.clientConnection = new ClientConnection();
        clientConnection.startConnection();
    }

    void input(ClientControlCommandType clientControlCommandType) {
        String command = formMessage(clientControlCommandType);

        clientConnection.sendMessage(command);

        if (clientControlCommandType == ClientControlCommandType.EXIT) {
            try {
                clientConnection.stopConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String formMessage(ClientControlCommandType clientControlCommandType) {
        return ClientCommandsTypeCoder.getCommand(clientControlCommandType);
    }
}
