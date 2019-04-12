package game;

import engine.GameEngine;
import engine.interaction.InteractionType;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import engine.player.command.PlayerCommand;
import engine.visualizer.Visualizer;
import game.object.Bonus;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.Type;
import game.object.tank.Tank;
import game.player.UserPlayerFactory;
import game.rule.BonusTankInteractionRule;
import game.rule.TankBulletInteractionRule;

public class Game {

    private final GameEngine gameEngine;

    private final UserPlayerFactory userPlayerFactory;

    public Game(Visualizer visualizer) {
        this.gameEngine = new GameEngine(visualizer);

        PhysicManager physicManager = gameEngine.getPhysicManager();

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameEngine.getTokenManager());
        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer(gameObjectFactory);

        gameObjectInitializer.createBonuses(bonusHolderObjectManager);

        gameEngine.addInteractionRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));

        gameEngine.addInteractionRule(new InteractionType(Type.BONUS, Type.TANK),
                new BonusTankInteractionRule(bonusHolderObjectManager, tankObjectManager));

        this.userPlayerFactory = new UserPlayerFactory(gameEngine, gameObjectFactory,
                tankObjectManager, bulletObjectManager);
    }

    public void processCommand(PlayerCommand playerCommand) {
        gameEngine.addPlayerCommand(playerCommand);
    }

    public void start() {
        gameEngine.start();
    }

    public void terminate() throws InterruptedException {
        gameEngine.terminate();
    }

    public UserPlayerFactory getUserPlayerFactory() {
        return userPlayerFactory;
    }
}
