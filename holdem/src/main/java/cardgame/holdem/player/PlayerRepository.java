package cardgame.holdem.player;

import java.util.Map;

public interface PlayerRepository {
    void save(Player player);
    Player findById(Long playerId);
    Map<Long, Player> getPlayerGroup();
}
