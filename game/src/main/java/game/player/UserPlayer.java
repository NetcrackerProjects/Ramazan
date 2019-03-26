package game.player;

import engine.command.EngineCommand;
import engine.geometry.Direction;
import engine.object.manager.ObjectManager;
import engine.player.Player;
import engine.player.command.PlayerCommandType;
import game.command.TankMoveEngineCommand;
import game.command.TankShootEngineCommand;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

public class UserPlayer implements Player {

    private final Tank tank;

    private final int id;

    private GameObjectFactory gameObjectFactory;
    private ObjectManager<Bullet> bulletObjectManager;

    UserPlayer(Tank tank, GameObjectFactory gameObjectFactory, ObjectManager<Bullet> bulletObjectManager, int id) {
        this.tank = tank;
        this.id = id;
        this.gameObjectFactory = gameObjectFactory;
        this.bulletObjectManager = bulletObjectManager;
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
            case SHOOT:
                return new TankShootEngineCommand(tank, bulletObjectManager, gameObjectFactory);
        }

        throw new IllegalArgumentException("unrecognized command id");
    }
}
