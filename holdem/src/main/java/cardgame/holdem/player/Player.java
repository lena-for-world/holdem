package cardgame.holdem.player;

import cardgame.holdem.carddeck.Card;

public class Player {

    Long id;
    int money;
    String nickname;
    Boolean dealer;

    public Player(Long id, int money, String nickname, Boolean dealer) {
        this.id = id;
        this.money = money;
        this.nickname = nickname;
        this.dealer = dealer;
    }

    public Long getId() {
        return id;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getNickname() {
        return nickname;
    }
    public Boolean getDealer() { return dealer; }

}

