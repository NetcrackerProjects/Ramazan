package server;

import database.Repository;
import database.RepositoryImplementation;
import database.exception.FailedDataBaseCreationException;
import game.Game;
import server.connection.control.ClientControlListener;
import server.connection.registration.ClientRegistrationListener;
import server.connection.data.DataSubscriberListener;
import server.connection.data.DataSubscriberManager;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.IOException;

class Server {

    private ClientControlListener clientControlListener;
    private ClientRegistrationListener clientRegistrationListener;
    private DataSubscriberListener dataSubscriberListener;

    private Game game;

    private Repository repository;

    Server() {
        try {
            this.repository = new RepositoryImplementation();
            UserManager userManager = new UserManager(repository);
            DataSubscriberManager dataSubscriberManager = new DataSubscriberManager(userManager);

            this.game = new Game(dataSubscriberManager);

            UserFactory userFactory = new UserFactory(game.getUserPlayerFactory(), repository);

            this.clientRegistrationListener = new ClientRegistrationListener(userFactory, userManager);

            this.clientControlListener = new ClientControlListener(game, userManager);

            this.dataSubscriberListener = new DataSubscriberListener(dataSubscriberManager);
        } catch (IOException | FailedDataBaseCreationException e) {
            e.printStackTrace();
        }
    }

    void start() {
        clientRegistrationListener.start();
        clientControlListener.start();
        dataSubscriberListener.start();
        game.start();
    }

    void stop() throws IOException, InterruptedException {
        clientRegistrationListener.terminate();
        clientControlListener.terminate();
        dataSubscriberListener.terminate();
        game.terminate();
        repository.clean();
    }
}