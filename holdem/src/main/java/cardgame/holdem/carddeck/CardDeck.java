package cardgame.holdem.carddeck;

import cardgame.holdem.game.Game;
import cardgame.holdem.tools.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// CardDeck은 한 게임 당 한 개여야 하고, 다른 게임방과 공유될 수 없음
public class CardDeck {

    String[] CardPattern = new String[] {"SPADE", "DIAMOND", "HEART", "CLOVER"};
    Integer[] used = new Integer[52];
    List<Card> cardList = new ArrayList<>();

    // 1==A 2-10 11==J 12==Q 13==K 14==JOKER
    public CardDeck() {
        for(int j = 0; j < 52; j++) {
            used[j] = 0;
        }
        for(int j = 0; j < 4; j++) {
            for (int i = 1; i <= 13; i++) {
                Card card;
                if(j == 0 || j == 3) card = new Card(CardPattern[j], i,true);
                else card = new Card(CardPattern[j], i, false);
                cardList.add(card);
            }
        }
       // cardList.add(new Card("JOKER", 14, true));
       // cardList.add(new Card("JOKER", 14, false));
    }

    public Integer[] getUsed() {
        return used;
    }

    public void setUsed(Integer[] used) {
        this.used = used;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

}
