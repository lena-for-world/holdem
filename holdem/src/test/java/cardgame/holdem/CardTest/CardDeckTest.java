package cardgame.holdem.CardTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import cardgame.holdem.CardTest.Card;
import cardgame.holdem.CardTest.CardDeck;

public class CardDeckTest {

    @Test
    @DisplayName("카드가 잘 만들어지는지 확인")
    void makingCard() {
        CardDeck cardDeck = new CardDeck();
        List<Card> testCardList = new ArrayList<>();
        testCardList = cardDeck.getCardList();
        for(Card temp : testCardList) {
            System.out.println("cardType = " + temp.getCardType() + " cardNumber = " + temp.getNumber() + " color = " + temp.getColor());
        }
        Assertions.assertThat(testCardList.size()).isEqualTo(52);

    }

}
