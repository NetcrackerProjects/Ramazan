package client.connection.control;

import client.gui.ClientUI;
import commons.ClientControlCommandType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class PlayerInput extends KeyAdapter {

    private final ClientControl clientControl;
    private final ClientUI clientUI;

    private final Map<Integer, ClientControlCommandType> keyCommandMap;

    public PlayerInput(ClientControl clientControl, ClientUI clientUI) {
        this.clientControl = clientControl;
        this.clientUI = clientUI;

        this.keyCommandMap = new HashMap<>();
        keyCommandMap.put(KeyEvent.VK_D, ClientControlCommandType.MOVE_RIGHT);
        keyCommandMap.put(KeyEvent.VK_A, ClientControlCommandType.MOVE_LEFT);
        keyCommandMap.put(KeyEvent.VK_W, ClientControlCommandType.MOVE_UP);
        keyCommandMap.put(KeyEvent.VK_S, ClientControlCommandType.MOVE_DOWN);
        keyCommandMap.put(KeyEvent.VK_SPACE, ClientControlCommandType.SHOOT);
        keyCommandMap.put(KeyEvent.VK_ESCAPE, ClientControlCommandType.EXIT);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        ClientControlCommandType type = keyCommandMap.get(keyEvent.getKeyCode());

        if (type == null) {
            return;
        }

        clientControl.sendCommand(type);

        if (type == ClientControlCommandType.EXIT) {
            clientUI.close();
        }
    }
}