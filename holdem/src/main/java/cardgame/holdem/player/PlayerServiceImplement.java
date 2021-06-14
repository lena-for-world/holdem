package cardgame.holdem.player;

import cardgame.holdem.game.Game;
import org.springframework.stereotype.Component;

public class PlayerServiceImplement implements PlayerService{
    public void infoCheck(Game gameService, Player player) {
        // gameService -> gameService에서 game에 등록된 오픈카드 다섯장의 정보 및 사용자에게 뽑힌 카드 두장의 정보 및 판돈 및 다른 사용자의 배팅 상황을 가져옴
        // Player -> Player자신만 볼 수 있는 카드 두 장을 확인하기 위해 본인 체크하려고
    }
}
