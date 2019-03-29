package client;

import engine.player.command.PlayerCommandType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class PlayerInput extends KeyAdapter {

    private final BlockingQueue<PlayerCommandType.Type> playerCommands;

    PlayerInput() {
        this.playerCommands = new LinkedBlockingQueue<>();
    }

    void drainsTo(Collection<PlayerCommandType.Type> collectionForDrain) {
        playerCommands.drainTo(collectionForDrain);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_D:
                playerCommands.add(PlayerCommandType.Type.MOVE_LEFT);
            case KeyEvent.VK_A:
                playerCommands.add(PlayerCommandType.Type.MOVE_RIGHT);
            case KeyEvent.VK_W:
                playerCommands.add(PlayerCommandType.Type.MOVE_UP);
            case KeyEvent.VK_S:
                playerCommands.add(PlayerCommandType.Type.MOVE_DOWN);
            case KeyEvent.VK_SPACE:
                playerCommands.add(PlayerCommandType.Type.SHOOT);
        }
    }
}