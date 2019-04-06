package client.connection.control;

public class ClientCommandEncoder {

    public static final String MOVE_LEFT_CODE = "1";
    public static final String MOVE_RIGHT_CODE = "2";
    public static final String MOVE_DOWN_CODE = "3";
    public static final String MOVE_UP_CODE = "4";
    public static final String SHOOT_CODE = "5";
    public static final String EXIT_CODE = "6";
    public static final String START_CODE = "7";

    static String getCommand(ClientControlCommandType clientControlCommandType) {
        switch (clientControlCommandType) {
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
            case EXIT:
                return EXIT_CODE;
            case START:
                return START_CODE;
        }

        throw new IllegalArgumentException();
    }
}
