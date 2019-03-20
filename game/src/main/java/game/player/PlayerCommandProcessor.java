package game.player;

import engine.command.Command;
import game.exception.CorruptPlayerCommandException;
import engine.exception.WrongObjectIdException;
import engine.geometry.Direction;
import game.command.TankMoveCommand;
import game.object.Tank;

public class PlayerCommandProcessor {

    private final PlayerManager playerManager;

    public PlayerCommandProcessor(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public Command getCommand(PlayerCommand playerCommand) throws CorruptPlayerCommandException {
        try {
            Player player = playerManager.getPlayer(playerCommand.getPlayerId());

            return getCommand(player.getTank(), playerCommand.getCommandId());
        } catch (WrongObjectIdException e) {
            throw new CorruptPlayerCommandException();
        }
    }

    private Command getCommand(Tank tank, int commandId) {
        switch (commandId) {
            case PlayerCommandType.MOVE_LEFT:
                return new TankMoveCommand(tank, Direction.Type.LEFT);
            case PlayerCommandType.MOVE_RIGHT:
                return new TankMoveCommand(tank, Direction.Type.RIGHT);
            case PlayerCommandType.MOVE_UP:
                return new TankMoveCommand(tank, Direction.Type.UP);
            case PlayerCommandType.MOVE_DOWN:
                return new TankMoveCommand(tank, Direction.Type.DOWN);
        }

        throw new IllegalArgumentException("unrecognized command id");
    }
}
