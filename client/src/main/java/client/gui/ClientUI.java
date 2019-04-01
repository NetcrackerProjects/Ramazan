package client.gui;

import client.control.ClientControl;
import client.control.PlayerInput;
import client.sprite.SpriteFactory;
import client.sprite.SpriteManager;
import engine.geometry.Vector;
import engine.object.TokenManager;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;

public class ClientUI extends JFrame {

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 300;

    public ClientUI(ClientControl clientControl) {
        SpriteManager spriteManager = new SpriteManager();

        GameObjectFactory gameObjectFactory = new GameObjectFactory(new TokenManager());
        Tank tank = gameObjectFactory.createTank(new Vector(0, 0), new Vector(100, 100));

        SpriteFactory spriteFactory = new SpriteFactory();
        spriteManager.addSprite(spriteFactory.createSprite(tank));

        PlayerInput playerInput = new PlayerInput(clientControl, this);

        add(new GamePanel(spriteManager, playerInput));

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);

        setTitle("client gui");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}