package engine;

import engine.action.Action;
import engine.action.ActionManager;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.interaction.InteractionProcessor;
import engine.interaction.InteractionRuleBase;
import engine.interaction.InteractionType;
import engine.interaction.rule.InteractionRule;
import engine.object.GameField;
import engine.object.TokenManager;
import engine.physic.PhysicManager;

import java.util.Collection;

public class GameEngine extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;

    private final InteractionRuleBase interactionRuleBase;

    private final TokenManager tokenManager;

    public GameEngine() {
        this.actionManager = new ActionManager();
        this.tokenManager = new TokenManager();

        this.physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10), tokenManager.getNewId()));

        this.interactionRuleBase = new InteractionRuleBase();
        this.interactionProcessor = new InteractionProcessor(interactionRuleBase);
    }

    @Override
    public void run() {
        double lag = 0.0;
        double previous = getCurrentTime();
        this.running = true;

        while (running) {
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

    public void terminate() throws InterruptedException {
        this.running = false;
        join();
    }

    public void addInteractionRule(InteractionType type, InteractionRule interactionRule) {
        interactionRuleBase.addRule(type, interactionRule);
    }

    public PhysicManager getPhysicManager() {
        return physicManager;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
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
