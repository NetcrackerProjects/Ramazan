package server.connection.visual;

import java.io.IOException;
import java.net.ServerSocket;

public class VisualSubscriberListener extends Thread {

    private final static int PORT = 7777;

    private final ServerSocket serverSocket;

    private final VisualSubscriberManager visualSubscriberManager;

    private volatile boolean running;

    public VisualSubscriberListener(VisualSubscriberManager visualSubscriberManager) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.visualSubscriberManager = visualSubscriberManager;
    }

    @Override
    public void run() {
        this.running = true;
        while (isRunning()) {
            try {
                new VisualSubscriberHandler(serverSocket.accept(), visualSubscriberManager).start();
            } catch (IOException e) {
                break;
            }
        }
    }

    public void terminate() throws IOException {
        serverSocket.close();
        visualSubscriberManager.terminate();
    }

    private boolean isRunning() {
        return running;
    }
}
