package engine.player.command;

import engine.command.EngineCommand;
import engine.command.EngineCommandFactory;
import engine.exception.WrongObjectIdException;
import engine.player.Player;
import engine.player.PlayerManager;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;

public class PlayerCommandProcessor {

    private static final int RETRIEVE_AMOUNT_PER_UPDATE = 100;

    private final PlayerManager playerManager;
    private final EngineCommandFactory engineCommandFactory;

    public PlayerCommandProcessor(PlayerManager playerManager, EngineCommandFactory engineCommandFactory) {
        this.playerManager = playerManager;
        this.engineCommandFactory = engineCommandFactory;
    }

    public Collection<EngineCommand> processPlayerCommands(BlockingQueue<PlayerCommand> playerCommands) {
        Collection<EngineCommand> engineCommands = new HashSet<>();

        Collection<PlayerCommand> commandsToProcess = new HashSet<>();
        playerCommands.drainTo(commandsToProcess, RETRIEVE_AMOUNT_PER_UPDATE);

        for (PlayerCommand playerCommand : commandsToProcess) {
            try {
                Player player = playerManager.getPlayer(playerCommand.getPlayerId());
                PlayerCommandType type = playerCommand.getCommandType();

                engineCommands.add(engineCommandFactory.createEngineCommand(player, type));
            } catch (WrongObjectIdException ignored) {
            }
        }

        return engineCommands;
    }
}
