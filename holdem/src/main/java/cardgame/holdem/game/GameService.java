package cardgame.holdem.game;

import cardgame.holdem.player.Player;
import cardgame.holdem.player.PlayerRepository;

import java.util.Map;

public interface GameService {

    Game start(Game game);

    Map<Long, Player> waiting(PlayerRepository playerRepository);

    void join(Player player);

    void whatHappensNow();

    void end();

}
