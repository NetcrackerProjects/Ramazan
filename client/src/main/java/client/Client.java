package client;

import client.connection.ClientRegistration;
import client.connection.control.ClientControl;
import client.gui.ClientUI;
import client.utils.VectorInt;

import java.awt.EventQueue;
import java.io.IOException;

class Client {

    private static final String DEFAULT_ADDRESS = "127.0.0.1";

    private static final VectorInt MONITOR_SIZE = new VectorInt(600, 400);

    private ClientUI clientUI;

    Client() throws IOException {
        int id = registerClient();

        ClientControl clientControl = new ClientControl(id);

        clientControl.start(DEFAULT_ADDRESS);

        EventQueue.invokeLater(() -> {
            this.clientUI = new ClientUI(clientControl, MONITOR_SIZE);
            clientUI.setVisible(true);
        });
    }

    private int registerClient() throws IOException {
        ClientRegistration clientRegistration = new ClientRegistration();
        return clientRegistration.registerClient(DEFAULT_ADDRESS, MONITOR_SIZE);
    }
}
