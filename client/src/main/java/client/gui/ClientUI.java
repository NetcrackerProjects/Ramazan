package client.gui;

import client.connection.control.ClientControl;
import client.connection.control.PlayerInput;
import client.sprite.SpriteManager;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;

public class ClientUI extends JFrame {

    public ClientUI(ClientControl clientControl, SpriteManager spriteManager,
                    int monitorWidth, int monitorHeight) {

        PlayerInput playerInput = new PlayerInput(clientControl, this);

        add(new GamePanel(spriteManager, playerInput));

        setSize(monitorWidth, monitorHeight);
        setResizable(false);

        setTitle("client gui");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}