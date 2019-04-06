package server.connection.control;

import client.connection.control.ClientCommandEncoder;
import client.connection.control.ClientControlCommandType;

class ClientCommandDecoder {

    static ClientControlCommandType getCommand(String message) {
        switch (message) {
            case ClientCommandEncoder.MOVE_LEFT_CODE:
                return ClientControlCommandType.MOVE_LEFT;
            case ClientCommandEncoder.MOVE_RIGHT_CODE:
                return ClientControlCommandType.MOVE_RIGHT;
            case ClientCommandEncoder.MOVE_DOWN_CODE:
                return ClientControlCommandType.MOVE_DOWN;
            case ClientCommandEncoder.MOVE_UP_CODE:
                return ClientControlCommandType.MOVE_UP;
            case ClientCommandEncoder.SHOOT_CODE:
                return ClientControlCommandType.SHOOT;
            case ClientCommandEncoder.START_CODE:
                return ClientControlCommandType.START;
            case ClientCommandEncoder.EXIT_CODE:
                return ClientControlCommandType.EXIT;
            default:
                return ClientControlCommandType.NONE;
        }
    }
}
