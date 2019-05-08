package client.connection.control;

import client.gui.ClientUI;
import commons.ClientPlayerCommandType;
import commons.ClientServerCommandType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class PlayerInput extends KeyAdapter {

    private final ClientControl clientControl;
    private final ClientUI clientUI;

    private final Map<Integer, ClientPlayerCommandType> keyPlayerCommandMap;
    private final Map<Integer, ClientServerCommandType> keyServerCommandMap;

    public PlayerInput(ClientControl clientControl, ClientUI clientUI) {
        this.clientControl = clientControl;
        this.clientUI = clientUI;

        this.keyPlayerCommandMap = new HashMap<>();
        keyPlayerCommandMap.put(KeyEvent.VK_D, ClientPlayerCommandType.MOVE_RIGHT);
        keyPlayerCommandMap.put(KeyEvent.VK_A, ClientPlayerCommandType.MOVE_LEFT);
        keyPlayerCommandMap.put(KeyEvent.VK_W, ClientPlayerCommandType.MOVE_UP);
        keyPlayerCommandMap.put(KeyEvent.VK_S, ClientPlayerCommandType.MOVE_DOWN);
        keyPlayerCommandMap.put(KeyEvent.VK_SPACE, ClientPlayerCommandType.SHOOT);

        this.keyServerCommandMap = new HashMap<>();
        keyServerCommandMap.put(KeyEvent.VK_ESCAPE, ClientServerCommandType.EXIT);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();

        ClientPlayerCommandType playerCommand = keyPlayerCommandMap.get(key);

        if (playerCommand != null) {
            clientControl.sendCommand(playerCommand);

            return;
        }

        ClientServerCommandType serverCommand = keyServerCommandMap.get(key);

        if (serverCommand != null) {
            clientControl.sendCommand(serverCommand);

            if (serverCommand == ClientServerCommandType.EXIT) {
                clientUI.close();
            }
        }
    }
}