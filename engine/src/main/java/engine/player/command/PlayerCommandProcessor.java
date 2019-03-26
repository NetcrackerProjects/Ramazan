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

    private static final int RETRIEVE_AMOUNT_PER_TERN = 100;

    private final PlayerManager playerManager;

    public PlayerCommandProcessor(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public Collection<EngineCommand> processPlayerCommands(BlockingQueue<PlayerCommand> playerCommands) {
        Collection<EngineCommand> engineCommands = new HashSet<>();

        int i = 0;

        while (playerCommands.size() > 0 && i < RETRIEVE_AMOUNT_PER_TERN) {
            try {
                PlayerCommand playerCommand = playerCommands.poll();

                if (playerCommand == null) {
                    break;
                }

                engineCommands.add(createEngineCommand(playerCommand));
            } catch (CorruptPlayerCommandException ignored) {
            }

            i++;
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
