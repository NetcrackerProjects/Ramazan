package game.player;

import engine.command.EngineCommand;
import engine.command.EngineCommandFactory;
import engine.exception.WrongObjectIdException;
import engine.geometry.Direction;
import engine.object.manager.ObjectManager;
import engine.player.Player;
import engine.player.command.PlayerCommandType;
import game.command.TankMoveEngineCommand;
import game.command.TankShootEngineCommand;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.tank.Tank;

public class EngineTankCommandFactory implements EngineCommandFactory {

    private final ObjectManager<Tank> tankManager;

    private final ObjectManager<Bullet> bulletManager;

    private final GameObjectFactory gameObjectFactory;

    public EngineTankCommandFactory(ObjectManager<Tank> tankManager,
                                    ObjectManager<Bullet> bulletManager, GameObjectFactory gameObjectFactory) {
        this.tankManager = tankManager;
        this.bulletManager = bulletManager;
        this.gameObjectFactory = gameObjectFactory;
    }

    @Override
    public EngineCommand createEngineCommand(Player player, PlayerCommandType playerCommandType) throws WrongObjectIdException {
        Tank tank = tankManager.getObjectById(player.getObjectId());

        switch (playerCommandType) {
            case MOVE_LEFT:
                return new TankMoveEngineCommand(tank, Direction.Type.LEFT);
            case MOVE_RIGHT:
                return new TankMoveEngineCommand(tank, Direction.Type.RIGHT);
            case MOVE_UP:
                return new TankMoveEngineCommand(tank, Direction.Type.UP);
            case MOVE_DOWN:
                return new TankMoveEngineCommand(tank, Direction.Type.DOWN);
            case SHOOT:
                return new TankShootEngineCommand(tank, bulletManager, gameObjectFactory);
        }

        throw new IllegalArgumentException("unrecognized command id");
    }
}
