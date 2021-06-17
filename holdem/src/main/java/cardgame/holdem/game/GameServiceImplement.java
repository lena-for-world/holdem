package cardgame.holdem.game;

import cardgame.holdem.carddeck.Card;
import cardgame.holdem.player.Player;
import cardgame.holdem.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GameServiceImplement implements GameService {

    private final PlayerRepository playerRepository;
    private final GameProgress gameProgress;

    @Autowired
    public GameServiceImplement(PlayerRepository playerRepository, GameProgress gameProgress) {
        this.playerRepository = playerRepository;
        this.gameProgress = gameProgress;
    }

    public void start(Game game) {
        System.out.println("game started!");
        gameProgress.playStage(game, GameProcess.PreFlop);
        gameProgress.playStage(game, GameProcess.Flop);
        gameProgress.playStage(game, GameProcess.Turn);
        gameProgress.playStage(game, GameProcess.River);
        whatHappensNow(game);
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
    public void whatHappensNow(Game game) {
        PlayerStatus[] ps = game.getStatus();
        System.out.println();
        System.out.println("현재 배팅액 : "+game.getTotalStake());
        System.out.println();
        Card[] c = game.getOpenCards();
        System.out.println("오픈 카드");
        for(int i= 0; i < 5; i++) {
            System.out.println(c[i].getCardType() + " " + c[i].getNumber() + " " + c[i].getColor());
        }
    }

    public int judge(Game game) {
        int winner = 0;
        // 우승 판정 -- 가진 카드와 오픈 카드를 조합하여 만들 수 있는 최상의 카드조합을 만든다 -> 참여인원의 최상의 카드조합을 비교하여 가장 우세한 사람이 승자!
        return winner;
    }
}
