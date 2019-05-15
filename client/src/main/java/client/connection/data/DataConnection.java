package client.connection.data;

import client.connection.ClientConnection;
import client.sprite.SpriteManager;
import engine.geometry.Vector;

import java.io.IOException;

public class DataConnection extends Thread {

    private static final int PORT = 7777;

    private final ClientConnection clientConnection;
    private final int clientId;

    private final String address;

    private final SpriteManager spriteManager;

    private volatile boolean running;

    public DataConnection(String address, int clientId, SpriteManager spriteManager) {
        this.clientConnection = new ClientConnection();
        this.clientId = clientId;
        this.spriteManager = spriteManager;
        this.address = address;
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

            SpriteDecoder spriteDecoder = new SpriteDecoder();

            spriteManager.setMonitorPosition(getMonitorPosition(splits[0]));

            for (int i = 1; i < splits.length; i++) {
                String split = splits[i];

                spriteManager.addSprite(spriteDecoder.loadSprite(split));
            }
        }
    }

    private Vector getMonitorPosition(String text) {
        String[] splits = text.split(":");
        return new Vector(Float.parseFloat(splits[0]), Float.parseFloat(splits[1]));
    }
}
