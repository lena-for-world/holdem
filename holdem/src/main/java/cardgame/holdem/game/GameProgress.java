package cardgame.holdem.game;

import cardgame.holdem.Stage.Flop;
import cardgame.holdem.Stage.PreFlop;
import cardgame.holdem.Stage.River;
import cardgame.holdem.Stage.Turn;
import cardgame.holdem.carddeck.Card;
import cardgame.holdem.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class GameProgress {

    PreFlop preFlop;
    Flop flop;
    Turn turn;
    River river;

    @Autowired
    public GameProgress(PreFlop preFlop, Flop flop, Turn turn, River river) {
        this.preFlop = preFlop;
        this.flop = flop;
        this.turn = turn;
        this.river = river;
    }

    public void playStage(Game game, GameProcess gameProcess) {
        if (gameProcess == GameProcess.PreFlop) {
            preFlop.preFlopStart(game);
        }
        if (gameProcess == GameProcess.Flop) {
            flop.flopStart(game);
        }
        if (gameProcess == GameProcess.Turn) {
            turn.turnStart(game);
        }
        if (gameProcess == GameProcess.River) {
            river.riverStart(game);
        }
    }
}
