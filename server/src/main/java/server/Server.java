package server;

import java.io.IOException;
import java.net.ServerSocket;

class Server extends Thread {

    private static final int PORT = 5555;

    private ServerSocket serverSocket;
    private volatile boolean running;

    private Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.running = true;
        while (isRunning()) {
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    void terminate() throws IOException, InterruptedException {
        this.running = false;
        serverSocket.close();
        join();
    }

    private boolean isRunning() {
        return running;
    }

    public static void main(String[] argv) {
        Server server = new Server(PORT);
        server.start();

        ServerCloserListener serverCloserListener = new ServerCloserListener(server);
        serverCloserListener.start();
    }
}