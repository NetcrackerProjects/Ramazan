package game.command;

import engine.action.Action;
import engine.command.EngineCommand;
import engine.geometry.Direction;
import engine.geometry.Vector;
import game.action.ChangeSpeedAction;
import game.object.tank.Tank;

public class TankMoveEngineCommand implements EngineCommand {

    private final Tank tank;
    private final Direction.Type direction;

    private static final double TANK_SPEED_MODULE = 1;

    public TankMoveEngineCommand(Tank tank, Direction.Type direction) {
        this.tank = tank;
        this.direction = direction;
    }

    @Override
    public Action getAction() {
        Vector newSpeed = Direction.getVector(direction);
        newSpeed.scale(TANK_SPEED_MODULE);
        return new ChangeSpeedAction(tank, newSpeed);
    }
}
