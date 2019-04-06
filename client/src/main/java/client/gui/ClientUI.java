package client.gui;

import client.connection.control.ClientControl;
import client.connection.control.PlayerInput;
import client.sprite.SpriteFactory;
import client.sprite.SpriteManager;
import client.uitls.VectorInt;
import engine.geometry.Vector;
import engine.object.TokenManager;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;

public class ClientUI extends JFrame {

    public ClientUI(ClientControl clientControl, VectorInt monitorSize) {
        SpriteManager spriteManager = new SpriteManager();

        GameObjectFactory gameObjectFactory = new GameObjectFactory(new TokenManager());
        Tank tank = gameObjectFactory.createTank(new Vector(0, 0), new Vector(100, 100));

        SpriteFactory spriteFactory = new SpriteFactory();
        spriteManager.addSprite(spriteFactory.createSprite(tank));

        PlayerInput playerInput = new PlayerInput(clientControl, this);

        add(new GamePanel(spriteManager, playerInput));

        setSize(monitorSize.getX(), monitorSize.getY());
        setResizable(false);

        setTitle("client gui");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}