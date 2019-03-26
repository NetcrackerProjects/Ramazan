package engine;

import engine.action.ActionManager;
import engine.command.EngineCommandProcessor;
import engine.geometry.Vector;
import engine.interaction.Interaction;
import engine.interaction.InteractionProcessor;
import engine.interaction.InteractionRuleBase;
import engine.interaction.InteractionType;
import engine.interaction.rule.InteractionRule;
import engine.object.GameField;
import engine.object.TokenManager;
import engine.physic.PhysicManager;
import engine.player.Player;
import engine.player.PlayerManager;
import engine.player.command.PlayerCommand;
import engine.player.command.PlayerCommandProcessor;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GameEngine extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;
    private final PlayerManager playerManager;

    private final EngineCommandProcessor engineCommandProcessor;
    private final PlayerCommandProcessor playerCommandProcessor;

    private final InteractionRuleBase interactionRuleBase;

    private final TokenManager tokenManager;

    private final BlockingQueue<PlayerCommand> playerCommands;

    public GameEngine() {
        this.actionManager = new ActionManager();
        this.tokenManager = new TokenManager();

        this.physicManager = new PhysicManager(new GameField(new Vector(0, 0),
                new Vector(10, 10), tokenManager.nextId()));

        this.interactionRuleBase = new InteractionRuleBase();
        this.interactionProcessor = new InteractionProcessor(interactionRuleBase);

        this.engineCommandProcessor = new EngineCommandProcessor();

        this.playerManager = new PlayerManager();

        this.playerCommands = new LinkedBlockingQueue<>();
        this.playerCommandProcessor = new PlayerCommandProcessor(playerManager);
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

    public void addPlayerCommand(PlayerCommand playerCommand) {
        playerCommands.add(playerCommand);
    }

    public void addPlayer(Player player) {
        playerManager.addPlayer(player);
    }

    private void update() {
        physicManager.applyForces();
        Collection<Interaction> interactions = physicManager.move();

        actionManager.processActions(interactionProcessor.processInteractions(interactions));

        actionManager.processActions(engineCommandProcessor.processCommands(playerCommandProcessor.processPlayerCommands(playerCommands)));
    }

    private double getCurrentTime() {
        return System.currentTimeMillis();
    }
}