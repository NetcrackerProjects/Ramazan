package game;

import engine.action.Action;
import engine.action.ActionManager;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.interaction.InteractionProcessor;
import engine.interaction.InteractionRuleBase;
import engine.interaction.InteractionType;
import game.object.Tank;
import game.object.Bullet;
import game.object.Bonus;
import game.object.Type;
import engine.object.GameField;
import engine.object.TokenManager;
import game.rule.BonusTankInteractionRule;
import game.rule.TankBulletInteractionRule;
import engine.object.manager.ObjectManager;
import engine.physic.PhysicManager;

import java.util.Collection;

public class Game extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;

    public Game() {
        this.actionManager = new ActionManager();
        TokenManager tokenManager = new TokenManager();
        this.physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10), tokenManager.getNewId()));

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer(tokenManager);

        gameObjectInitializer.createTanks(tankObjectManager);
        gameObjectInitializer.createBullets(bulletObjectManager);
        gameObjectInitializer.createBonuses(bonusHolderObjectManager);

        InteractionRuleBase interactionRuleBase = new InteractionRuleBase();

        interactionRuleBase.addRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(tankObjectManager, bulletObjectManager));
        interactionRuleBase.addRule(new InteractionType(Type.BONUS, Type.TANK),
                new BonusTankInteractionRule(bonusHolderObjectManager, tankObjectManager));

        this.interactionProcessor = new InteractionProcessor(interactionRuleBase);
    }

    @Override
    public void run() {
        double lag = 0.0;
        double previous = getCurrentTime();

        while (isRunning()) {
            double current = getCurrentTime();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= MS_PER_UPDATE) {
                update();
                lag -= MS_PER_UPDATE;
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void terminate() throws InterruptedException {
        this.running = false;
        join();
    }

    private void update() {
        Collection<Interaction> interactions = physicManager.move();
        Collection<Action> actions = interactionProcessor.processInteractions(interactions);
        actionManager.processActions(actions);
    }

    private double getCurrentTime() {
        return System.currentTimeMillis();
    }
}
