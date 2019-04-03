package server;

import java.io.IOException;
import java.net.ServerSocket;

class Server {

    static final int PORT = 5555;

    private ServerSocket serverSocket;
    private volatile boolean running;

    Server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        this.running = true;
        while (isRunning()) {
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                break;
            }
        }
    }

    void stop() {
        this.running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isRunning() {
        return running;
    }
}