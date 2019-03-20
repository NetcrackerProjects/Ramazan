package game.player;

public class PlayerCommand {

    private final int playerId;
    private final int commandId;

    public PlayerCommand(int playerId, int commandId) {
        this.playerId = playerId;
        this.commandId = commandId;
    }

    int getPlayerId() {
        return playerId;
    }

    int getCommandId() {
        return commandId;
    }
}
