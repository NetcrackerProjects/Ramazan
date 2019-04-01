package client;

import client.control.ClientControl;
import client.gui.ClientUI;

import java.awt.EventQueue;
import java.io.IOException;

class Client {

    private ClientUI clientUI;

    Client() throws IOException {
        ClientControl clientControl = new ClientControl();

        EventQueue.invokeLater(() -> {
            this.clientUI = new ClientUI(clientControl);
            clientUI.setVisible(true);
        });
    }
}
