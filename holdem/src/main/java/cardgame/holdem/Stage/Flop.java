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
public class Flop {

    @Autowired
    public Flop() {
    }

    public void flopStart(Game game) {
        int betMethod;
        int temp, j = 0;
        int stake = 0;
        int totalStake = game.getTotalStake();
        List<Card> cardList = game.getCardDeck().getCardList();
        Card[] openCards = game.getOpenCards();
        Integer[] used = game.getCardDeck().getUsed();
        Map<Long, Player> members = game.getParticipants();
        List<Long> keys = new ArrayList<>(game.getParticipants().keySet()); // 게임 참가자들의 key값
        PlayerStatus[] status = game.getStatus();
        Random rand = new Random();

        // 3장의 오픈카드를 깜
        j = 0;
        while(j < 3) {
            temp = rand.nextInt(52);
            if(used[temp] != 0) continue;
            else {
                used[temp] = 1;
                openCards[j] = cardList.get(temp);
                j++;
            }
        }
        // 배팅 시작
        for(int i = 0; i < game.getParticipants().size(); i++) {
            betMethod = rand.nextInt(2);
            // sb or call
            if(betMethod == 0) {
                int money = members.get(keys.get(i)).getMoney();
                String name = members.get(keys.get(i)).getNickname();
                System.out.println(name +"이(가) 가진 돈 = " + money);
                stake = 5000;
                System.out.println(name +"이(가) 배팅한 돈 = " + stake);
                members.get(keys.get(i)).setMoney(money-stake);
                totalStake += stake;
            }
            // raise
            else if(betMethod == 1) {
                int money = members.get(keys.get(i)).getMoney();
                String name = members.get(keys.get(i)).getNickname();
                System.out.println(name +"이(가) 가진 돈 = " + money);
                if(stake != 0) stake *= 2;
                else stake = 5000;
                System.out.println(name +"이(가) 배팅한 돈 = " + stake);
                members.get(keys.get(i)).setMoney(money-stake);
                totalStake += stake;
            }
               /* // fold는 만들지 않음
                else {
                    String name = members.get(keys.get(i)).getNickname();
                    status[i] = PlayerStatus.Die;
                    System.out.println(name+"(이)가 기권");
                }*/
        }
        game.getCardDeck().setUsed(used);
        game.setOpenCards(openCards);
        game.setTotalStake(totalStake);
        game.setStatus(status);
    }
}
