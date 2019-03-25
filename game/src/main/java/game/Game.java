package game;

import engine.GameEngine;
import engine.interaction.InteractionType;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import game.object.Bonus;
import game.object.Bullet;
import game.object.GameObjectFactory;
import game.object.Tank;
import game.object.Type;
import engine.player.command.PlayerCommand;
import game.player.UserPlayerFactory;
import engine.player.command.PlayerCommandType;
import game.rule.BonusTankInteractionRule;
import game.rule.TankBulletInteractionRule;

class Game {

    private final GameEngine gameEngine;

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

        UserPlayerFactory userPlayerFactory = new UserPlayerFactory(gameObjectFactory, tankObjectManager);

        gameEngine.addPlayer(userPlayerFactory.createPlayer());
    }

    private void processCommand(PlayerCommand playerCommand) {
        gameEngine.addPlayerCommand(playerCommand);
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

        game.processCommand(new PlayerCommand(0, PlayerCommandType.Type.MOVE_DOWN));

        try {
            game.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
