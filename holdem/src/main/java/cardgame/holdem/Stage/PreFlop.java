package cardgame.holdem.Stage;

import cardgame.holdem.carddeck.Card;
import cardgame.holdem.game.Game;
import cardgame.holdem.game.PlayerStatus;
import cardgame.holdem.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Component
public class PreFlop {

    @Autowired
    public PreFlop() {
    }

    public void preFlopStart(Game game) {
        int betMethod;
        int temp, j = 0;
        int stake = 0;
        int totalStake = game.getTotalStake();
        List<Card> cardList = game.getCardDeck().getCardList();
        Card[][] playerCards = game.getPlayerCards();
        Card[] openCards = game.getOpenCards();
        Integer[] used = game.getCardDeck().getUsed();
        Map<Long, Player> members = game.getParticipants();
        List<Long> keys = new ArrayList<>(game.getParticipants().keySet()); // 게임 참가자들의 key값
        PlayerStatus[] status = game.getStatus();
        Random rand = new Random();

        for(int i = 0; i < game.getParticipants().size(); i++) status[i] = PlayerStatus.Alive;
        for(int i = 0; i < 2; i++) {
            betMethod = rand.nextInt(2);
            // small blind or call
            if( i == 0 || betMethod == 0) {
                int money = members.get(keys.get(i)).getMoney();
                String name = members.get(keys.get(i)).getNickname();
                System.out.println(name +"이(가) 가진 돈 = " + money);
                stake = 5000;
                System.out.println(name +"이(가) 배팅한 돈 = " + stake);
                members.get(keys.get(i)).setMoney(money-stake);
                totalStake += stake;
            }
            // raise
            else {
                int money = members.get(keys.get(i)).getMoney();
                String name = members.get(keys.get(i)).getNickname();
                System.out.println(name +"이(가) 가진 돈 = " + money);
                stake *= 2;
                System.out.println(name +"이(가) 배팅한 돈 = " + stake);
                members.get(keys.get(i)).setMoney(money-stake);
                totalStake += stake;
            }
        }
        // 모든 멤버들에게 카드를 2장씩 나누어줌
        for(int i = 0 ; i < game.getParticipants().size(); i++) {
            j = 0;
            // player당 2장씩 배포
            while(j < 2) {
                temp = rand.nextInt(52);
                if(used[temp] != 0) continue;
                else {
                    used[temp] = 1;
                    playerCards[i][j] = cardList.get(temp);
                    j++;
                }
            }
        }
        game.getCardDeck().setUsed(used);
        game.setPlayerCards(playerCards);
        game.setTotalStake(totalStake);
    }
}
