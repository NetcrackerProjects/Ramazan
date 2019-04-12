package server;

import game.Game;
import server.connection.control.ClientControlListener;
import server.connection.registration.ClientRegistrationListener;
import server.connection.visual.VisualSubscriberListener;
import server.connection.visual.VisualSubscriberManager;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.IOException;

class Server {

    private ClientControlListener clientControlListener;
    private ClientRegistrationListener clientRegistrationListener;
    private VisualSubscriberListener visualSubscriberListener;

    private Game game;

    Server() {
        try {
            UserManager userManager = new UserManager();
            VisualSubscriberManager visualSubscriberManager = new VisualSubscriberManager(userManager);

            this.game = new Game(visualSubscriberManager);

            UserFactory userFactory = new UserFactory(game.getUserPlayerFactory());

            this.clientRegistrationListener = new ClientRegistrationListener(userFactory, userManager);

            this.clientControlListener = new ClientControlListener(game);

            this.visualSubscriberListener = new VisualSubscriberListener(visualSubscriberManager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        clientRegistrationListener.start();
        clientControlListener.start();
        visualSubscriberListener.start();
        game.start();
    }

    void stop() throws IOException, InterruptedException {
        clientRegistrationListener.terminate();
        clientControlListener.terminate();
        visualSubscriberListener.terminate();
        game.terminate();
    }
}