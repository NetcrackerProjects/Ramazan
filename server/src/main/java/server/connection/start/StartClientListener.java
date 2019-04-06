package server.connection.start;

import server.user.UserFactory;
import server.user.UserManager;

import java.io.IOException;
import java.net.ServerSocket;

public class StartClientListener extends Thread {

    private final static int PORT = 6666;

    private final ServerSocket serverSocket;

    private final UserFactory userFactory;
    private final UserManager userManager;

    private volatile boolean running;

    public StartClientListener(UserFactory userFactory, UserManager userManager) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.userFactory = userFactory;
        this.userManager = userManager;
    }

    @Override
    public void run() {
        this.running = true;
        while (isRunning()) {
            try {
                new StartClientHandler(serverSocket.accept(), userFactory, userManager).start();
            } catch (IOException e) {
                break;
            }
        }
    }

    public void terminate() throws IOException {
        serverSocket.close();
    }

    private boolean isRunning() {
        return running;
    }
}
