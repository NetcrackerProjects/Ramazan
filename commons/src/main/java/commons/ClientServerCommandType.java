package commons;

import java.util.HashMap;
import java.util.Map;

public enum ClientServerCommandType {
    EXIT(1),
    START(2),
    NONE(3);

    private static Map<Integer, ClientServerCommandType> TYPE_MAP;

    private final int code;

    ClientServerCommandType(int code) {
        this.code = code;
        addToMap(this, code);
    }

    public static ClientServerCommandType getType(int code) {
        ClientServerCommandType type = TYPE_MAP.get(code);

        if (type == null) {
            throw new IllegalArgumentException();
        }

        return type;
    }

    public static int valueOf(ClientServerCommandType type) {
        return type.code;
    }

    private static void addToMap(ClientServerCommandType type, int code) {
        if (TYPE_MAP == null) {
            TYPE_MAP = new HashMap<>();
        }
        TYPE_MAP.put(code, type);
    }
}
