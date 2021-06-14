package cardgame.holdem.CardTest;

public class Card {
    String cardType;
    Integer number;
    Boolean color; // true black, false red

    public Card(String cardType, Integer number, Boolean color) {
        this.cardType = cardType;
        this.number = number;
        this.color = color;
    }

    public String getCardType() {
        return cardType;
    }

    public Integer getNumber() {
        return number;
    }

    public Boolean getColor() {
        return color;
    }
}
