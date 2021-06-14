package cardgame.holdem.carddeck;

public class Card {

    String shape;
    Integer number;
    Boolean color; // true black, false red

    public Card(String shape, Integer number, Boolean color) {
        this.shape = shape;
        this.number = number;
        this.color = color;
    }

    public String getCardType() {
        return shape;
    }

    public Integer getNumber() {
        return number;
    }

    public Boolean getColor() {
        return color;
    }
}
