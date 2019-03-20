package game.player;

import engine.exception.WrongObjectIdException;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private final Map<Integer, Player> playerMap;

    public PlayerManager() {
        this.playerMap = new HashMap<>();
    }

    public void addPlayer(Player player) {
        playerMap.put(player.getId(), player);
    }

    Player getPlayer(int playerId) throws WrongObjectIdException {
        Player player = playerMap.get(playerId);

        if (player == null) {
            throw new WrongObjectIdException();
        }

        return player;
    }
}
