package client;

import client.connection.ClientRegistration;
import client.connection.control.ClientControl;
import client.connection.data.DataConnection;
import client.gui.ClientUI;
import client.sprite.SpriteManager;
import commons.ClientControlCommandType;

import java.awt.EventQueue;
import java.io.IOException;

class Client {

    private static final String DEFAULT_ADDRESS = "127.0.0.1";

    private static final int MONITOR_WIDTH = 400;
    private static final int MONITOR_HEIGHT = 400;

    private ClientUI clientUI;

    Client() throws IOException {
        int id = registerClient();

        SpriteManager spriteManager = new SpriteManager();

        DataConnection dataConnection = new DataConnection(DEFAULT_ADDRESS, id, spriteManager);
        dataConnection.start();

        ClientControl clientControl = new ClientControl(id, dataConnection);

        clientControl.start(DEFAULT_ADDRESS);

        clientControl.sendCommand(ClientControlCommandType.START);

        EventQueue.invokeLater(() -> {
            this.clientUI = new ClientUI(clientControl, spriteManager, MONITOR_WIDTH, MONITOR_HEIGHT);
            clientUI.setVisible(true);
        });
    }

    private int registerClient() throws IOException {
        ClientRegistration clientRegistration = new ClientRegistration();
        return clientRegistration.registerClient(DEFAULT_ADDRESS, MONITOR_WIDTH, MONITOR_HEIGHT);
    }
}
