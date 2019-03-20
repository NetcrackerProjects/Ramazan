package game;

import engine.GameEngine;
import game.exception.CorruptPlayerCommandException;
import engine.interaction.InteractionType;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import game.object.Bonus;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.Tank;
import game.object.Type;
import game.player.PlayerCommand;
import game.player.PlayerCommandProcessor;
import game.player.PlayerFactory;
import game.player.PlayerManager;
import game.rule.BonusTankInteractionRule;
import game.rule.TankBulletInteractionRule;

class Game {

    private final GameEngine gameEngine;

    private final PlayerCommandProcessor playerCommandProcessor;

    private Game() {
        this.gameEngine = new GameEngine();

        PhysicManager physicManager = gameEngine.getPhysicManager();

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        GameObjectFactory gameObjectFactory = new GameObjectFactory(gameEngine.getTokenManager());
        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer(gameObjectFactory);

        gameObjectInitializer.createTanks(tankObjectManager);
        gameObjectInitializer.createBullets(bulletObjectManager);
        gameObjectInitializer.createBonuses(bonusHolderObjectManager);

        gameEngine.addInteractionRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));

        gameEngine.addInteractionRule(new InteractionType(Type.BONUS, Type.TANK),
                new BonusTankInteractionRule(bonusHolderObjectManager, tankObjectManager));

        PlayerManager playerManager = new PlayerManager();
        this.playerCommandProcessor = new PlayerCommandProcessor(playerManager);

        PlayerFactory playerFactory = new PlayerFactory(gameObjectFactory, tankObjectManager);

        playerManager.addPlayer(playerFactory.createPlayer());
    }

    private void processCommand(PlayerCommand playerCommand) throws CorruptPlayerCommandException {
        gameEngine.addCommand(playerCommandProcessor.getCommand(playerCommand));
    }

    private void start() {
        gameEngine.start();
    }

    private void terminate() throws InterruptedException {
        gameEngine.terminate();
    }

    public static void main(String[] argv) {
        Game game = new Game();
        game.start();

        try {
            game.processCommand(new PlayerCommand(0, 1));
        } catch (CorruptPlayerCommandException e) {
            e.printStackTrace();
        }

        try {
            game.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
