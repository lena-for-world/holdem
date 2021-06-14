package cardgame.holdem.game;

import cardgame.holdem.carddeck.CardDeck;
import cardgame.holdem.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

public class Game {

    // Game을 구성하는 요소는 맞는데... 현재 방식은 GameServiceImpl에서 싱글톤인 스프링 빈 생성 시에 Game 클래스를 주입해줘야해서 Game도 컴포넌트로 만들어야 함
    // 원래는 Game을 스프링 빈으로 만들지 않고 start마다 생성할 계획이었음... 이 경우 생성된 게임을 저장할 메모리가 필요할듯
    // ==> 빈으로 만들까 했지만 역시 목적에 어긋남!! 저렇게는 생성자 주입도 불가
    // ===> 현재 방식을 변경하고 여기는 그대로 놔두기로 결정

    private Map<Long, Player> participants;
    private CardDeck cardDeck;
    private int totalStake, stake;
    private GameProcess gameProcess;

    public Game(Map<Long, Player> participants, CardDeck cardDeck, int totalStake, int stake, GameProcess gameProcess) {
        this.participants = participants;
        this.cardDeck = cardDeck;
        this.totalStake = totalStake;
        this.stake = stake;
        this.gameProcess = gameProcess;
    }

    public enum CardType {
        PlayerCard, OpenCard
    }

    public Map<Long, Player> getParticipants() {
        return participants;
    }

    public int getTotalStake() {
        return totalStake;
    }

    public int getStake() {
        return stake;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }
}
