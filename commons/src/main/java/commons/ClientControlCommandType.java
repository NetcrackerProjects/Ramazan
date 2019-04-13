package commons;

import java.util.HashMap;
import java.util.Map;

public enum ClientControlCommandType {
    MOVE_LEFT(1),
    MOVE_RIGHT(2),
    MOVE_DOWN(3),
    MOVE_UP(4),
    SHOOT(5),
    EXIT(6),
    START(7),
    NONE(8);

    private static Map<Integer, ClientControlCommandType> TYPE_MAP;

    private final int code;

    ClientControlCommandType(int code) {
        this.code = code;
        addToMap(this, code);
    }

    public static ClientControlCommandType getType(int code) {
        ClientControlCommandType type = TYPE_MAP.get(code);

        if (type == null) {
            throw new IllegalArgumentException();
        }

        return type;
    }

    public static int valueOf(ClientControlCommandType type) {
        return type.code;
    }

    private static void addToMap(ClientControlCommandType type, int code) {
        if (TYPE_MAP == null) {
            TYPE_MAP = new HashMap<>();
        }
        TYPE_MAP.put(code, type);
    }
}
