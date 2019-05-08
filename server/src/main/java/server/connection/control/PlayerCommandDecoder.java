package server.connection.control;

import commons.ClientPlayerCommandType;
import engine.player.command.PlayerCommandType;

class PlayerCommandDecoder {

    static PlayerCommandType getPlayerCommand(ClientPlayerCommandType clientCommand) {
        switch (clientCommand) {

            case MOVE_LEFT:
                return PlayerCommandType.MOVE_LEFT;
            case MOVE_RIGHT:
                return PlayerCommandType.MOVE_RIGHT;
            case MOVE_UP:
                return PlayerCommandType.MOVE_UP;
            case MOVE_DOWN:
                return PlayerCommandType.MOVE_DOWN;
            case SHOOT:
                return PlayerCommandType.SHOOT;
        }

        throw new IllegalArgumentException();
    }
}
