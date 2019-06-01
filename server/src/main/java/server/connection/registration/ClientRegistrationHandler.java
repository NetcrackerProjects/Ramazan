package server.connection.registration;

import server.exception.FailedCreateUserException;
import server.user.User;
import server.user.UserFactory;
import server.user.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String name = in.readLine();

            int id = registerNewClient(name);

            out.println(id);

            terminate();
        } catch (IOException | FailedCreateUserException e) {
            e.printStackTrace();
        }
    }

    private void terminate() throws IOException {
        socket.close();
    }

    private int registerNewClient(String name) throws FailedCreateUserException {
        User user = userFactory.createUser(name);
        userManager.addUser(user);

        return user.getId();
    }
}
