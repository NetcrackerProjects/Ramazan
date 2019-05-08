package server.connection.registration;

import server.exception.FailedCreateUserException;
import server.user.User;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class ClientRegistrationHandler extends Thread {

    private final Socket socket;

    private final UserFactory userFactory;
    private final UserManager userManager;

    ClientRegistrationHandler(Socket socket, UserFactory userFactory, UserManager userManager) {
        this.socket = socket;
        this.userFactory = userFactory;
        this.userManager = userManager;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int id = registerNewClient();

            out.println(id);

            terminate();
        } catch (IOException | FailedCreateUserException e) {
            e.printStackTrace();
        }
    }

    private void terminate() throws IOException {
        socket.close();
    }

    private int registerNewClient() throws FailedCreateUserException {
        User user = userFactory.createUser();
        userManager.addUser(user);

        return user.getId();
    }
}
