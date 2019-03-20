package game;

import engine.GameEngine;
import engine.geometry.Direction;
import engine.interaction.InteractionType;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;
import game.command.TankMoveCommand;
import game.object.Bonus;
import game.object.Bullet;
import game.object.Tank;
import game.object.Type;
import game.rule.BonusTankInteractionRule;
import game.rule.TankBulletInteractionRule;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Game {

    private final GameEngine gameEngine;

    private final Tank player;

    private Game() {
        this.gameEngine = new GameEngine();

        PhysicManager physicManager = gameEngine.getPhysicManager();

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer(gameEngine.getTokenManager());

        gameObjectInitializer.createTanks(tankObjectManager);
        gameObjectInitializer.createBullets(bulletObjectManager);
        gameObjectInitializer.createBonuses(bonusHolderObjectManager);

        gameEngine.addInteractionRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));

        gameEngine.addInteractionRule(new InteractionType(Type.BONUS, Type.TANK),
                new BonusTankInteractionRule(bonusHolderObjectManager, tankObjectManager));

        this.player = gameObjectInitializer.createPlayer(tankObjectManager);
    }

    private void randomCommand() {
        Random random = ThreadLocalRandom.current();
        gameEngine.addCommand(new TankMoveCommand(player, Direction.Type.values()[random.nextInt(4)]));
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

        game.randomCommand();

        try {
            game.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
