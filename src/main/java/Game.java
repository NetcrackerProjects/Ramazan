import action.Action;
import action.ActionManager;
import geometry.Rectangle;
import geometry.Vector;
import interaction.GameObjectInteraction;
import interaction.InteractionProcessor;
import interaction.InteractionRuleBase;
import interaction.InteractionType;
import interaction.rule.BonusTankInteractionRule;
import interaction.rule.TankBulletInteractionRule;
import object.Type;
import object.manager.GameObjectManager;
import physic.PhysicManager;

import java.util.Collection;

class Game extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;

    Game() {
        GameObjectManager gameObjectManager = new GameObjectManager();

        this.physicManager = new PhysicManager(new Rectangle(new Vector(0, 0), new Vector(100, 100)),
                gameObjectManager.getPhysicObjectManager());
        this.actionManager = new ActionManager();

        InteractionRuleBase interactionRuleBase = new InteractionRuleBase();

        interactionRuleBase.addRule(new InteractionType(Type.TANK, Type.BULLET),
                new TankBulletInteractionRule(gameObjectManager));
        interactionRuleBase.addRule(new InteractionType(Type.TANK, Type.BONUS),
                new BonusTankInteractionRule(gameObjectManager));

        this.interactionProcessor = new InteractionProcessor(interactionRuleBase);

        GameInitializer gameInitializer = new GameInitializer();
        gameInitializer.initialize(gameObjectManager, physicManager);
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

    boolean isRunning() {
        return running;
    }

    void terminate() throws InterruptedException {
        this.running = false;
        join();
    }

    private void update() {
        Collection<GameObjectInteraction> interactions = physicManager.move();
        Collection<Action> actions = interactionProcessor.processInteractions(interactions);
        actionManager.processActions(actions);
    }

    private double getCurrentTime() {
        return System.currentTimeMillis();
    }
}
