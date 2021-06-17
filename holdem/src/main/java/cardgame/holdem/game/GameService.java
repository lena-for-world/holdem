package cardgame.holdem.game;

import cardgame.holdem.player.Player;
import cardgame.holdem.player.PlayerRepository;

import java.util.Map;

public interface GameService {

    void start(Game game);

    Map<Long, Player> waiting(PlayerRepository playerRepository);

    void join(Player player);

    void whatHappensNow(Game game);

    void end();

    int judge(Game game);

}
