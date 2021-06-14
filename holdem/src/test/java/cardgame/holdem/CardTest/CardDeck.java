package cardgame.holdem.CardTest;

import cardgame.holdem.CardTest.Card;

import java.util.LinkedList;
import java.util.List;

public class CardDeck {
    String[] CardPattern = new String[] {"SPADE", "DIAMOND", "HEART", "CLOVER"};
    List<Card> cardList = new LinkedList<>();

    public CardDeck() {
        for(int j = 0; j < 4; j++) {
            for (int i = 1; i <= 13; i++) {
                Card card;
                if(j == 0 || j == 3) card = new Card(CardPattern[j], i,true);
                else card = new Card(CardPattern[j], i, false);
                cardList.add(card);
            }
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
