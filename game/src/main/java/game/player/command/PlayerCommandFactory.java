package game.player.command;

import engine.command.EngineCommand;
import game.exception.CorruptPlayerCommandException;
import engine.exception.WrongObjectIdException;
import engine.geometry.Direction;
import game.command.TankMoveEngineCommand;
import game.object.Tank;
import game.player.Player;
import game.player.PlayerManager;

public class PlayerCommandFactory {

    private final PlayerManager playerManager;

    public PlayerCommandFactory(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public EngineCommand createEngineCommand(PlayerCommand playerCommand) throws CorruptPlayerCommandException {
        try {
            Player player = playerManager.getPlayer(playerCommand.getPlayerId());

            return createEngineCommand(player.getTank(), playerCommand.getCommandType());
        } catch (WrongObjectIdException e) {
            throw new CorruptPlayerCommandException();
        }
    }

    private EngineCommand createEngineCommand(Tank tank, PlayerCommandType.Type commandType) {
        switch (commandType) {
            case MOVE_LEFT:
                return new TankMoveEngineCommand(tank, Direction.Type.LEFT);
            case MOVE_RIGHT:
                return new TankMoveEngineCommand(tank, Direction.Type.RIGHT);
            case MOVE_UP:
                return new TankMoveEngineCommand(tank, Direction.Type.UP);
            case MOVE_DOWN:
                return new TankMoveEngineCommand(tank, Direction.Type.DOWN);
        }

        throw new IllegalArgumentException("unrecognized command id");
    }
}
