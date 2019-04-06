package client;

import client.connection.UserRegistration;
import client.connection.control.ClientControl;
import client.gui.ClientUI;
import client.uitls.VectorInt;

import java.awt.EventQueue;
import java.io.IOException;

class Client {

    private static final String DEFAULT_ADDRESS = "127.0.0.1";

    private static final VectorInt monitorSize = new VectorInt(600, 400);

    private ClientUI clientUI;

    Client() throws IOException {
        int id = registerUser();

        ClientControl clientControl = new ClientControl(id);

        clientControl.start(DEFAULT_ADDRESS);

        EventQueue.invokeLater(() -> {
            this.clientUI = new ClientUI(clientControl, monitorSize);
            clientUI.setVisible(true);
        });
    }

    private int registerUser() throws IOException {
        UserRegistration userRegistration = new UserRegistration();
        return userRegistration.initializeConnection(DEFAULT_ADDRESS, monitorSize);
    }
}
