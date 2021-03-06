package server.connection.control;

import game.Game;
import server.user.UserManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.HashSet;

public class ClientControlListener extends Thread {

    private static final int PORT = 5555;

    private final ServerSocket serverSocket;

    private final Collection<ClientControlHandler> controlHandlers;

    private final Game game;

    private final UserManager userManager;

    private volatile boolean running;

    public ClientControlListener(Game game, UserManager userManager) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.controlHandlers = new HashSet<>();
        this.game = game;
        this.userManager = userManager;
    }

    @Override
    public void run() {
        this.running = true;
        while(running) {
            try {
                ClientControlHandler controlHandler =
                        new ClientControlHandler(serverSocket.accept(), game, userManager);
                controlHandlers.add(controlHandler);
                controlHandler.start();
            } catch (IOException e) {
                break;
            }
        }
    }

    public void terminate() throws IOException {
        this.running = false;
        serverSocket.close();

        for(ClientControlHandler controlHandler: controlHandlers) {
            controlHandler.terminate();
        }
    }
}
