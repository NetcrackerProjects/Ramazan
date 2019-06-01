package server.connection.control;

import commons.ClientPlayerCommandType;
import commons.ClientServerCommandType;
import engine.player.command.PlayerCommand;
import engine.player.command.PlayerCommandType;
import game.Game;
import server.user.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientControlHandler extends Thread {

    private static final String PLAYER_COMMAND_TYPE = "p";
    private static final String SERVER_COMMAND_TYPE = "s";

    private static final int NO_ID = -1;

    private final Game game;

    private final Socket clientSocket;

    private int userId;

    private final UserManager userManager;

    ClientControlHandler(Socket clientSocket, Game game, UserManager userManager) {
        this.clientSocket = clientSocket;
        this.game = game;
        this.userId = NO_ID;
        this.userManager = userManager;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;

            while((message = in.readLine()) != null) {
                processMessage(message);
            }

        } catch (IOException ignored) {
        }
    }

    void terminate() throws IOException {
        clientSocket.close();
    }

    private void processMessage(String message) {
        String[] splits = message.split(":");

        if (splits.length > 3) {
            throw new IllegalArgumentException();
        }

        if (splits[0].equals(PLAYER_COMMAND_TYPE)) {
            processPlayerCommand(splits);
        }

        if (splits[0].equals(SERVER_COMMAND_TYPE)) {
            processServerCommand(splits);
        }
    }

    private void processPlayerCommand(String[] args) {
        ClientPlayerCommandType command = ClientPlayerCommandType.getType(Integer.parseInt(args[1]));

        if (ClientControlHandler.isRegistered(userId)) {
            return;
        }

        PlayerCommandType playerCommandType = PlayerCommandDecoder.getPlayerCommand(command);

        PlayerCommand playerCommand = new PlayerCommand(userId, playerCommandType);
        game.processCommand(playerCommand);
    }

    private void processServerCommand(String[] args) {
        ClientServerCommandType command = ClientServerCommandType.getType(Integer.parseInt(args[1]));

        switch(command) {

            case NONE:
                break;
            case START:
                this.userId = Integer.parseInt((args[2]));
                break;
            case EXIT:
                try {
                    userManager.saveUser(userId);
                    terminate();
                } catch (IOException ignored) {
                }
                break;
        }
    }

    private static boolean isRegistered(int userId) {
        return (userId == NO_ID);
    }
}
