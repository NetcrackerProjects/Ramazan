package client.sprite;

import engine.player.command.PlayerCommandType;

import java.util.Collection;

public class PlayerCommandsTypeCoder {

    private static final int NONE_CODE = 0;
    private static final int MOVE_LEFT_CODE = 1;
    private static final int MOVE_RIGHT_CODE = 2;
    private static final int MOVE_DOWN_CODE = 3;
    private static final int MOVE_UP_CODE = 4;
    private static final int SHOOT_CODE = 5;

    public static String getMessage(Collection<PlayerCommandType.Type> playerCommandTypes) {
        StringBuilder message = new StringBuilder();

        for (PlayerCommandType.Type type : playerCommandTypes) {
            int code = getCode(type);
            message.append(code);
        }

        if (playerCommandTypes.isEmpty()) {
            message.append(NONE_CODE);
        }

        return message.toString();
    }

    private static int getCode(PlayerCommandType.Type playerCommandType) {
        switch (playerCommandType) {
            case MOVE_LEFT:
                return MOVE_LEFT_CODE;
            case MOVE_RIGHT:
                return MOVE_RIGHT_CODE;
            case MOVE_UP:
                return MOVE_UP_CODE;
            case MOVE_DOWN:
                return MOVE_DOWN_CODE;
            case SHOOT:
                return SHOOT_CODE;
        }

        throw new IllegalArgumentException();
    }
}
