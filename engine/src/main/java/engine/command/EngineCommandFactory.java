package engine.command;

import engine.exception.WrongObjectIdException;
import engine.player.Player;
import engine.player.command.PlayerCommandType;

public interface EngineCommandFactory {

    EngineCommand createEngineCommand(Player player, PlayerCommandType playerCommandType) throws WrongObjectIdException;
}
