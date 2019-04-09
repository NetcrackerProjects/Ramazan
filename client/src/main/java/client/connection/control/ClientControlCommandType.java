package client.connection.control;

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

    private static Map<Integer, ClientControlCommandType> map;

    private final int code;

    ClientControlCommandType(int code) {
        this.code = code;
        addToMap(this, code);
    }

    public int getCode() {
        return code;
    }

    public static ClientControlCommandType getType(int code) {
        ClientControlCommandType type = map.get(code);

        if (type == null) {
            throw new IllegalArgumentException();
        }

        return type;
    }

    private static void addToMap(ClientControlCommandType type, int code) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(code, type);
    }
}
