package server.connection.data;

import java.io.IOException;
import java.net.ServerSocket;

public class DataSubscriberListener extends Thread {

    private final static int PORT = 7777;

    private final ServerSocket serverSocket;

    private final DataSubscriberManager dataSubscriberManager;

    private volatile boolean running;

    public DataSubscriberListener(DataSubscriberManager dataSubscriberManager) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.dataSubscriberManager = dataSubscriberManager;
    }

    @Override
    public void run() {
        this.running = true;
        while (isRunning()) {
            try {
                new DataSubscriberHandler(serverSocket.accept(), dataSubscriberManager).start();
            } catch (IOException e) {
                break;
            }
        }
    }

    public void terminate() throws IOException {
        serverSocket.close();
        dataSubscriberManager.terminate();
    }

    private boolean isRunning() {
        return running;
    }
}
