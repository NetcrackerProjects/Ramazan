package server;

import game.Game;
import server.connection.control.ClientControlListener;
import server.connection.start.StartClientListener;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.IOException;

class Server {

    private ClientControlListener clientControlListener;
    private StartClientListener startClientListener;

    private Game game;

    Server() {
        try {
            this.game = new Game();
            UserFactory userFactory = new UserFactory(game.getUserPlayerFactory());
            UserManager userManager = new UserManager();

            this.startClientListener = new StartClientListener(userFactory, userManager);

            this.clientControlListener = new ClientControlListener(game);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        startClientListener.start();
        clientControlListener.start();
        game.start();
    }

    void stop() throws IOException, InterruptedException {
        startClientListener.terminate();
        clientControlListener.terminate();
        game.terminate();
    }
}