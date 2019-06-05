package game;

import engine.GameEngine;
import engine.interaction.InteractionType;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import engine.player.command.PlayerCommand;
import engine.publisher.Publisher;
import game.object.Bonus;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.Type;
import game.object.tank.Tank;
import game.player.EngineTankCommandFactory;
import game.player.UserFactory;
import game.rule.BonusTankInteractionRule;
import game.rule.TankBulletInteractionRule;

public class Game {

    private final GameEngine gameEngine;

    private final UserFactory userFactory;

    public Game(Publisher publisher) {
        this.gameEngine = new GameEngine(publisher);

        PhysicManager physicManager = gameEngine.getPhysicManager();

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameEngine.getTokenManager());
        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer(gameObjectFactory);

        gameEngine.setEngineCommandFactory(new EngineTankCommandFactory(tankObjectManager,
                                                                    bulletObjectManager, gameObjectFactory));

        gameObjectInitializer.createBonuses(bonusHolderObjectManager);

        gameEngine.addInteractionRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));

        gameEngine.addInteractionRule(new InteractionType(Type.BONUS, Type.TANK),
                new BonusTankInteractionRule(bonusHolderObjectManager, tankObjectManager));

        this.userFactory = new UserFactory(gameEngine, gameObjectFactory,
                tankObjectManager);
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

    public UserFactory getUserFactory() {
        return userFactory;
    }
}
