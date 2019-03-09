import action.Action;
import action.ActionManager;
import geometry.Rectangle;
import geometry.Vector;
import interaction.GameObjectInteraction;
import interaction.InteractionProcessor;
import physic.PhysicManager;

import java.util.Collection;

class Game extends Thread {

    private final static int MS_PER_UPDATE = 30;
    private volatile boolean running = true;

    private final PhysicManager physicManager;
    private final InteractionProcessor interactionProcessor;
    private final ActionManager actionManager;

    Game() {
        GameFacade gameFacade = new GameFacade();
        gameFacade.createGame(new Rectangle(new Vector(0, 0), new Vector(100, 100)));
        this.actionManager = gameFacade.getActionManager();
        this.physicManager = gameFacade.getPhysicManager();
        this.interactionProcessor = gameFacade.getInteractionProcess();
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
