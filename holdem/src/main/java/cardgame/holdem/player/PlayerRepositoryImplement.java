package cardgame.holdem.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PlayerRepositoryImplement implements PlayerRepository {

    @Autowired
    public PlayerRepositoryImplement() {
    }

    private static Map<Long, Player> playerGroup = new HashMap<>();

    public void save(Player player) {
        playerGroup.put(player.getId(), player);
    }

    public Player findById(Long playerId) {
        return playerGroup.get(playerId);
    }

    public Map<Long, Player> getPlayerGroup() {
        return playerGroup;
    }
}
