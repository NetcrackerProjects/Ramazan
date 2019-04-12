package client.connection.visual;

import client.connection.ClientConnection;
import client.sprite.SpriteFactory;
import client.sprite.SpriteManager;
import engine.geometry.Rectangle;
import engine.geometry.Vector;

import java.io.IOException;

public class VisualConnection extends Thread {

    private static final int PORT = 7777;

    private final ClientConnection clientConnection;
    private final int clientId;

    private final String address;

    private final SpriteManager spriteManager;

    private final SpriteFactory spriteFactory;

    private volatile boolean running;

    public VisualConnection(String address, int clientId, SpriteManager spriteManager) {
        this.clientConnection = new ClientConnection();
        this.clientId = clientId;
        this.spriteManager = spriteManager;
        this.address = address;
        this.spriteFactory = new SpriteFactory();
    }

    @Override
    public void run() {
        try {
            clientConnection.startConnection(address, PORT);

            clientConnection.sendMessage(String.valueOf(clientId));

            this.running = true;

            while(running) {
                updateSprites();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminate() {
        try {
            this.running = false;
            clientConnection.stopConnection();
        } catch (IOException ignored) {
        }
    }

    private void updateSprites() throws IOException {
        String message = clientConnection.receiveMessage();

        synchronized (spriteManager) {
            spriteManager.clean();

            String[] splits = message.split(";");

            GameObjectDecoder gameObjectDecoder = new GameObjectDecoder();

            spriteManager.setMonitorPosition(getMonitorPosition(splits[0]));

            for (int i = 1; i < splits.length; i++) {
                String split = splits[i];
                gameObjectDecoder.loadGameObject(split);

                Rectangle body = gameObjectDecoder.getBody();
                int typeId = gameObjectDecoder.getTypeId();

                spriteManager.addSprite(spriteFactory.createSprite(body, typeId));
            }
        }
    }

    private Vector getMonitorPosition(String text) {
        String[] splits = text.split(":");
        return new Vector(Float.parseFloat(splits[0]), Float.parseFloat(splits[1]));
    }
}
