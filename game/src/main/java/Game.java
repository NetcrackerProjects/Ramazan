import action.Action;
import action.ActionManager;
import geometry.Vector;
import interaction.Interaction;
import interaction.InteractionProcessor;
import interaction.InteractionRuleBase;
import interaction.InteractionType;
import object.*;
import rule.BonusTankInteractionRule;
import rule.TankBulletInteractionRule;
import object.manager.ObjectManager;
import physic.PhysicManager;

import java.util.Collection;

class Game extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;

    Game() {
        this.actionManager = new ActionManager();
        this.physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10)));

        ObjectManager<Tank> tankObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bullet> bulletObjectManager = new ObjectManager<>(physicManager);
        ObjectManager<Bonus> bonusHolderObjectManager = new ObjectManager<>(physicManager);

        GameObjectInitializer gameObjectInitializer = new GameObjectInitializer();

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

    boolean isRunning() {
        return running;
    }

    void terminate() throws InterruptedException {
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
