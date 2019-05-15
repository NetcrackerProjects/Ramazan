package client.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String address, int port) throws IOException {
        this.clientSocket = new Socket(address, port);
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage(String command) {
        out.println(command);
    }

    public String receiveMessage() throws IOException {
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        clientSocket.close();
    }
}
