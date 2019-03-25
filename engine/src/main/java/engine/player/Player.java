package engine.player;

import engine.command.EngineCommand;
import engine.player.command.PlayerCommandType;

public interface Player {

    EngineCommand getEngineCommand(PlayerCommandType.Type playerCommand);

    int getId();
}
