package engine.player.command;

public class PlayerCommand {

    private final int playerId;
    private final PlayerCommandType commandType;

    public PlayerCommand(int playerId, PlayerCommandType commandType) {
        this.playerId = playerId;
        this.commandType = commandType;
    }

    int getPlayerId() {
        return playerId;
    }

    PlayerCommandType getCommandType() {
        return commandType;
    }
}
