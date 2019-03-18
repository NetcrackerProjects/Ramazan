package engine;

import engine.action.Action;
import engine.action.ActionManager;
import engine.command.Command;
import engine.command.CommandProcessor;
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
import java.util.HashSet;

public class GameEngine extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;
    private final CommandProcessor commandProcessor;

    private final InteractionRuleBase interactionRuleBase;

    private final TokenManager tokenManager;

    private final Collection<Command> commands;

    public GameEngine() {
        this.actionManager = new ActionManager();
        this.tokenManager = new TokenManager();

        this.physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10), tokenManager.nextId()));

        this.interactionRuleBase = new InteractionRuleBase();
        this.interactionProcessor = new InteractionProcessor(interactionRuleBase);

        this.commands = new HashSet<>();
        this.commandProcessor = new CommandProcessor();
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

    public void addCommand(Command command) {
        commands.add(command);
    }

    private void update() {
        physicManager.applyForces();
        Collection<Interaction> interactions = physicManager.move();
        Collection<Action> actions = interactionProcessor.processInteractions(interactions);
        actionManager.processActions(actions);

        actions = commandProcessor.processCommands(commands);
        actionManager.processActions(actions);
    }

    private double getCurrentTime() {
        return System.currentTimeMillis();
    }
}