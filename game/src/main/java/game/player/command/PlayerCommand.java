package game.player.command;

public class PlayerCommand {

    private final int playerId;
    private final PlayerCommandType.Type commandType;

    public PlayerCommand(int playerId, PlayerCommandType.Type commandType) {
        this.playerId = playerId;
        this.commandType = commandType;
    }

    int getPlayerId() {
        return playerId;
    }

    PlayerCommandType.Type getCommandType() {
        return commandType;
    }
}
