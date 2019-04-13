package server.connection.registration;

import engine.geometry.Vector;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String line = in.readLine();

            int id = registerNewClient(parseMonitorSize(line));

            out.println(id);

            terminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void terminate() throws IOException {
        socket.close();
    }

    private Vector parseMonitorSize(String line) {
        String[] splits = line.split(":");

        if (splits.length != 2) {
            throw new IllegalArgumentException();
        }

        int width = Integer.parseInt(splits[0]);
        int height = Integer.parseInt(splits[1]);
        return new Vector(width, height);
    }

    private int registerNewClient(Vector monitorSize) {
        User user = userFactory.createUser(monitorSize);
        userManager.addUser(user);

        return user.getId();
    }
}