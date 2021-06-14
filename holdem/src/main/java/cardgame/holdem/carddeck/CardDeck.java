package cardgame.holdem.carddeck;

import cardgame.holdem.game.Game;
import cardgame.holdem.tools.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// CardDeck은 한 게임 당 한 개여야 하고, 다른 게임방과 공유될 수 없음
public class CardDeck {

    String[] CardPattern = new String[] {"SPADE", "DIAMOND", "HEART", "CLOVER"};
    Integer[] used = new Integer[54];
    List<Card> cardList = new ArrayList<>();

    public CardDeck() {
        for(int j = 0; j < 54; j++) {
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
        cardList.add(new Card("JOKER", 14, true));
        cardList.add(new Card("JOKER", 14, false));
    }
    // 카드를 랜덤으로 뽑음
    // 파라미터로 뽑는 카드의 수 들어감
    // 오픈카드인지, 플레이어 카드인지
   /* Card draw(int howMany) {
        // 랜덤으로 뽑고, 뽑은거 표시
    }
*/

}
