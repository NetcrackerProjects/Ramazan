package server.connection.control;

import commons.ClientControlCommandType;
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

        ClientControlCommandType command = ClientControlCommandType.getType(Integer.parseInt(splits[0]));

        switch(command) {

            case NONE:
                return;
            case START:
                this.userId = Integer.parseInt((splits[1]));
                return;
            case EXIT:
                try {
                    terminate();
                } catch (IOException ignored) {
                }
                return;
        }

        if (userIsNotStartedYet()) {
            return;
        }

        PlayerCommandType playerCommandType = PlayerCommandDecoder.getPlayerCommand(command);

        PlayerCommand playerCommand = new PlayerCommand(userId, playerCommandType);
        game.processCommand(playerCommand);
    }

    private boolean userIsNotStartedYet() {
        return (userId == NO_ID);
    }
}
