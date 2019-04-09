package client;

import client.connection.ClientRegistration;
import client.connection.control.ClientControl;
import client.gui.ClientUI;

import java.awt.EventQueue;
import java.io.IOException;

class Client {

    private static final String DEFAULT_ADDRESS = "127.0.0.1";

    private static final int MONITOR_WIDTH = 600;
    private static final int MONITOR_HEIGHT = 400;

    private ClientUI clientUI;

    Client() throws IOException {
        int id = registerClient();

        ClientControl clientControl = new ClientControl(id);

        clientControl.start(DEFAULT_ADDRESS);

        EventQueue.invokeLater(() -> {
            this.clientUI = new ClientUI(clientControl, MONITOR_WIDTH, MONITOR_HEIGHT);
            clientUI.setVisible(true);
        });
    }

    private int registerClient() throws IOException {
        ClientRegistration clientRegistration = new ClientRegistration();
        return clientRegistration.registerClient(DEFAULT_ADDRESS, MONITOR_WIDTH, MONITOR_HEIGHT);
    }
}
