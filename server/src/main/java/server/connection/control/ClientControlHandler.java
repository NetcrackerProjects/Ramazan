package server.connection.control;

import client.connection.control.ClientControlCommandType;
import engine.player.command.PlayerCommand;
import engine.player.command.PlayerCommandType;
import game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ClientControlHandler extends Thread {

    private static final int NO_ID = -1;

    private final Game game;

    private final Socket clientSocket;

    private int userId;

    ClientControlHandler(Socket clientSocket, Game game) {
        this.clientSocket = clientSocket;
        this.game = game;
        this.userId = NO_ID;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;

            while((message = in.readLine()) != null) {
                processMessage(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void terminate() throws IOException {
        clientSocket.close();
    }

    private void processMessage(String message) {
        String[] splits = message.split(":");

        if (splits.length > 2) {
            throw new IllegalArgumentException();
        }

        ClientControlCommandType command = ClientCommandDecoder.getCommand(splits[0]);

        if (command == ClientControlCommandType.NONE) {
            return;
        }

        if (command == ClientControlCommandType.EXIT) {
            try {
                terminate();
            } catch (IOException ignored) {
            }
            return;
        }

        if (command == ClientControlCommandType.START) {
            this.userId = getUserId(splits[1]);
            return;
        }

        if (userId == NO_ID) {
            return;
        }

        PlayerCommandType playerCommandType = PlayerCommandDecoder.getPlayerCommand(command);

        PlayerCommand playerCommand = new PlayerCommand(userId, playerCommandType);
        game.processCommand(playerCommand);
    }

    private int getUserId(String id) {
        return Integer.parseInt(id);
    }
}
