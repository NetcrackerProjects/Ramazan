package engine.player.command;

import engine.command.EngineCommand;
import engine.exception.CorruptPlayerCommandException;
import engine.player.Player;
import engine.player.PlayerManager;
import engine.exception.WrongObjectIdException;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;

public class PlayerCommandProcessor {

    private static final int RETRIEVE_AMOUNT_PER_UPDATE = 100;

    private final PlayerManager playerManager;

    public PlayerCommandProcessor(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public Collection<EngineCommand> processPlayerCommands(BlockingQueue<PlayerCommand> playerCommands) {
        Collection<EngineCommand> engineCommands = new HashSet<>();

        Collection<PlayerCommand> commandsToProcess = new HashSet<>();
        playerCommands.drainTo(commandsToProcess, RETRIEVE_AMOUNT_PER_UPDATE);

        for (PlayerCommand playerCommand : commandsToProcess) {
            try {
                engineCommands.add(createEngineCommand(playerCommand));
            } catch (CorruptPlayerCommandException ignored) {
            }
        }

        return engineCommands;
    }

    private EngineCommand createEngineCommand(PlayerCommand playerCommand) throws CorruptPlayerCommandException {
        try {
            Player player = playerManager.getPlayer(playerCommand.getPlayerId());

            return player.getEngineCommand(playerCommand.getCommandType());
        } catch (WrongObjectIdException e) {
            throw new CorruptPlayerCommandException();
        }
    }
}
