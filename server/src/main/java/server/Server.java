package server;

import database.UserRepository;
import database.exception.RepositoryException;
import game.Game;
import server.connection.control.ClientControlListener;
import server.connection.registration.ClientRegistrationListener;
import server.connection.data.DataSubscriberListener;
import server.connection.data.DataSubscriberManager;
import server.user.UserManager;

import java.io.IOException;

class Server {

    private ClientControlListener clientControlListener;
    private ClientRegistrationListener clientRegistrationListener;
    private DataSubscriberListener dataSubscriberListener;

    private Game game;

    private UserRepository repository;

    Server() {
        try {
            this.repository = new UserRepository();
            UserManager userManager = new UserManager(repository);
            DataSubscriberManager dataSubscriberManager = new DataSubscriberManager(userManager);

            this.game = new Game(dataSubscriberManager);

            this.clientRegistrationListener = new ClientRegistrationListener(game.getUserFactory(), userManager);

            this.clientControlListener = new ClientControlListener(game, userManager);

            this.dataSubscriberListener = new DataSubscriberListener(dataSubscriberManager);
        } catch (IOException | RepositoryException e) {
            e.printStackTrace();
        }
    }

    void start() {
        clientRegistrationListener.start();
        clientControlListener.start();
        dataSubscriberListener.start();
        game.start();
    }

    void stop() throws IOException, InterruptedException, RepositoryException {
        clientRegistrationListener.terminate();
        clientControlListener.terminate();
        dataSubscriberListener.terminate();
        game.terminate();
        repository.clear();
    }
}