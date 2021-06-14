package cardgame.holdem.game;

import cardgame.holdem.player.Player;
import cardgame.holdem.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameServiceImplement implements GameService {

    private final PlayerRepository playerRepository;

    @Autowired
    public GameServiceImplement(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Game start(Game game) {
        //game.setStage(0);
        System.out.println("game started!");
        return game;
    }

    public Map<Long, Player> waiting(PlayerRepository playerRepository) {
        // (테스트용) 가입된 회원(4명) 모두 게임 참여자로 정함
        return playerRepository.getPlayerGroup();
    }

    public void join(Player player) {
        playerRepository.save(player);
    }

    public void end() {

    }
    public void whatHappensNow() {

    }
}
