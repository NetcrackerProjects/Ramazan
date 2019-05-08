package commons;

import java.util.HashMap;
import java.util.Map;

public enum ClientPlayerCommandType {
    MOVE_LEFT(1),
    MOVE_RIGHT(2),
    MOVE_DOWN(3),
    MOVE_UP(4),
    SHOOT(5);

    private static Map<Integer, ClientPlayerCommandType> TYPE_MAP;

    private final int code;

    ClientPlayerCommandType(int code) {
        this.code = code;
        addToMap(this, code);
    }

    public static ClientPlayerCommandType getType(int code) {
        ClientPlayerCommandType type = TYPE_MAP.get(code);

        if (type == null) {
            throw new IllegalArgumentException();
        }

        return type;
    }

    public static int valueOf(ClientPlayerCommandType type) {
        return type.code;
    }

    private static void addToMap(ClientPlayerCommandType type, int code) {
        if (TYPE_MAP == null) {
            TYPE_MAP = new HashMap<>();
        }
        TYPE_MAP.put(code, type);
    }
}
