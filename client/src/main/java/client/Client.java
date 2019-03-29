package client;

import client.sprite.Sprite;
import client.sprite.SpriteFactory;
import client.sprite.SpriteManager;
import engine.geometry.Vector;
import engine.object.TokenManager;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

import java.awt.EventQueue;
import java.io.IOException;

class Client {

    private static final String DEFAULT_ID = "127.0.0.1";
    private static final int DEFAULT_PORT = 5555;

    private ClientUI clientUI;
    private final SpriteManager spriteManager;
    private final PlayerInput playerInput;

    private Client() {
        this.spriteManager = new SpriteManager();
        this.playerInput = new PlayerInput();

        GameObjectFactory gameObjectFactory = new GameObjectFactory(new TokenManager());

        Tank tank = gameObjectFactory.createTank(new Vector(0, 0), new Vector(100, 100));
        SpriteFactory spriteFactory = new SpriteFactory();

        Sprite sprite = spriteFactory.createSprite(tank);
        spriteManager.addSprite(sprite);

        EventQueue.invokeLater(() -> {
            this.clientUI = new ClientUI(spriteManager, playerInput);
            clientUI.setVisible(true);
        });

        ClientControl clientControl = new ClientControl(playerInput);

        try {
            clientControl.startConnection(DEFAULT_ID, DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        new Client();
    }

}
