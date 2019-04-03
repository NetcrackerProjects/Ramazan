package client.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class ClientConnection {

    private static final String DEFAULT_ADDRESS = "127.0.0.1";
    private static final int DEFAULT_PORT = 5555;

    private Socket clientSocket;
    private PrintWriter out;

    void startConnection() throws IOException {
        this.clientSocket = new Socket(DEFAULT_ADDRESS, DEFAULT_PORT);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);

        sendCommand(ClientCommandsEncoder.getCommand(ClientControlCommandType.START));
    }

    void sendCommand(String command) {
        out.println(command);
    }

    void stopConnection() throws IOException {
        clientSocket.close();
    }
}
