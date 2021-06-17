package cardgame.holdem.JoinAndWaitTest;

import cardgame.holdem.AutoAppConfig;
import cardgame.holdem.carddeck.CardDeck;
import cardgame.holdem.game.Game;
import cardgame.holdem.game.GameProcess;
import cardgame.holdem.game.GameService;
import cardgame.holdem.game.GameServiceImplement;
import cardgame.holdem.player.Player;
import cardgame.holdem.player.PlayerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class joinPlayer {

    GameService gameService;
    PlayerRepository playerRepository;

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    @DisplayName("참여 확인")
    void joinAndStart() {
        gameService = ac.getBean(GameService.class);
        playerRepository = ac.getBean(PlayerRepository.class);
        Player player1 = new Player(1L, 60000, "Karina", true);
        Player player2 = new Player(2L, 30000, "Jiselle", false);
        Player player3 = new Player(3L, 15000, "Winter", false);
        Player player4 = new Player(4L, 50000, "NingNing", false);

        gameService.join(player1);
        gameService.join(player2);
        gameService.join(player3);
        gameService.join(player4);
        Map<Long, Player> m = playerRepository.getPlayerGroup();
        Assertions.assertThat(m.size()).isEqualTo(4);


        Game newGame = new Game(gameService.waiting(playerRepository), new CardDeck(), 0, GameProcess.PreFlop);
        gameService.start(newGame);
        Map<Long, Player> newGameParticipants = newGame.getParticipants();
        for(Map.Entry<Long, Player> participant : newGameParticipants.entrySet()) {
            System.out.println("participant = " + participant.getValue().getNickname());
        }
    }
}
