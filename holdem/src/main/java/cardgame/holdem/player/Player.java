package cardgame.holdem.player;

public class Player {

    Long id;
    int money;
    String nickname;

    public Player(Long id, int money, String nickname) {
        this.id = id;
        this.money = money;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }
    public int getMoney() {
        return money;
    }

    public String getNickname() {
        return nickname;
    }
}

