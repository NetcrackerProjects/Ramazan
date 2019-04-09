package server;

import game.Game;
import server.connection.control.ClientControlListener;
import server.connection.registration.ClientRegistrationListener;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.IOException;

class Server {

    private ClientControlListener clientControlListener;
    private ClientRegistrationListener clientRegistrationListener;

    private Game game;

    Server() {
        try {
            this.game = new Game();
            UserFactory userFactory = new UserFactory(game.getUserPlayerFactory());
            UserManager userManager = new UserManager();

            this.clientRegistrationListener = new ClientRegistrationListener(userFactory, userManager);

            this.clientControlListener = new ClientControlListener(game);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        clientRegistrationListener.start();
        clientControlListener.start();
        game.start();
    }

    void stop() throws IOException, InterruptedException {
        clientRegistrationListener.terminate();
        clientControlListener.terminate();
        game.terminate();
    }
}