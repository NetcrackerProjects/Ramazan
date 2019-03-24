package game.player;

import engine.command.EngineCommand;
import engine.geometry.Direction;
import engine.player.Player;
import engine.player.command.PlayerCommandType;
import game.command.TankMoveEngineCommand;
import game.object.Tank;

public class UserPlayer implements Player {

    private final Tank tank;

    private final int id;

    UserPlayer(Tank tank, int id) {
        this.tank = tank;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public EngineCommand getEngineCommand(PlayerCommandType.Type playerCommandType) {
        return createEngineCommand(playerCommandType);
    }

    private EngineCommand createEngineCommand(PlayerCommandType.Type commandType) {
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
