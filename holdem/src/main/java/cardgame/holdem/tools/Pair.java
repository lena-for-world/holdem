package cardgame.holdem.tools;

public class Pair {
    Integer a, b;

    public Pair(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public Integer first() {
        return a;
    }

    public Integer second() {
        return b;
    }
}
