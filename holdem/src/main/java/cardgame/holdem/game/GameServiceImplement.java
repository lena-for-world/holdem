package cardgame.holdem.game;

import cardgame.holdem.carddeck.Card;
import cardgame.holdem.player.Player;
import cardgame.holdem.player.PlayerRepository;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class GameServiceImplement implements GameService {

    private final PlayerRepository playerRepository;
    private final GameProgress gameProgress;

    @Autowired
    public GameServiceImplement(PlayerRepository playerRepository, GameProgress gameProgress) {
        this.playerRepository = playerRepository;
        this.gameProgress = gameProgress;
    }

    public void start(Game game) {
        System.out.println("game started!");
        gameProgress.playStage(game, GameProcess.PreFlop);
        gameProgress.playStage(game, GameProcess.Flop);
        gameProgress.playStage(game, GameProcess.Turn);
        gameProgress.playStage(game, GameProcess.River);
        whatHappensNow(game);
        int winnerI = judge(game);
        List<Long> keys = new ArrayList<>(game.getParticipants().keySet());
        Long winnerL = keys.get(winnerI);
        Player winner = game.getParticipants().get(winnerL);
        int money = winner.getMoney();
        winner.setMoney(money + game.getTotalStake());
        System.out.println("winner '" + winner.getNickname() + "' takes " + game.getTotalStake() + "!!");
    }

    public Map<Long, Player> waiting(PlayerRepository playerRepository) {
        // (테스트용) 가입된 회원(4명) 모두 게임 참여자로 정함
        return playerRepository.getPlayerGroup();
    }

    public void join(Player player) {
        playerRepository.save(player);
    }

    public void end() {

    }
    public void whatHappensNow(Game game) {
        PlayerStatus[] ps = game.getStatus();
        System.out.println();
        System.out.println("현재 배팅액 : "+game.getTotalStake());
        System.out.println();
        Card[] c = game.getOpenCards();
        System.out.println("오픈 카드");
        for(int i= 0; i < 5; i++) {
            System.out.println(c[i].getCardType() + " " + c[i].getNumber() + " " + c[i].getColor());
        }
    }

    public int getBestCard(Vector<Vector<Integer>> collections, Card[] openCards, Card[] pCards) {

        int highest = 0;
        Card[] temp = new Card[5];
        temp[0] = pCards[0];
        temp[1] = pCards[1];
        int[] newTemp = new int[5];
        System.out.println(collections.get(0).size());
        for(int i = 0; i < collections.size(); i++) {
            for(int j = 0; j < collections.get(i).size(); j++) temp[j+2] = openCards[collections.get(i).get(j)];
            // 숫자 비교 위해 newTemp에 저장
            for(int j = 0; j < 5; j++) {
                newTemp[j] = temp[j].getNumber();
            }
            Arrays.sort(newTemp); // 정렬
            for(int j = 0; j < 5; j++) {
                System.out.print(newTemp[j] + " ");
            }
            System.out.println();
            // temp 에 저장한 카드 조합으로 족보 탐색
            String shape = "";
            int flag = 1;
            int score = 0;
            // 로얄 스트레이트 플러쉬(240) or 백 스트레이트 플러쉬(220) or 스트레이트 플러쉬(200)
            for(int j = 0; j < 5; j++) {
                if(j == 0) shape = temp[j].getCardType();
                else {
                    if(shape != temp[j].getCardType()) flag = 0;
                }
            }
            // 모두 같은 무늬의 카드일 때
            if(flag == 1) {
                int newFlag = 1;
                for(int j = 0; j < 5; j++) {
                    if(j == 0) continue;
                    if((newTemp[j-1] +1) != newTemp[j]) newFlag = 0;
                }
                // 만약 카드 숫자가 연달아 뽑혔다면 newFlag는 1
                if(newFlag == 1) {
                    if (newTemp[0] == 10) score = 240; // 로티플
                    else if (newTemp[0] == 1) score = 220; // 백스플
                    else score = 200; // 스티플
                }
                // 연달아 뽑지는 못했지만 무늬는 같은 경우
                else {
                    score = 140; // 플러쉬
                }
            }
            // 무늬가 다를 때
            else {
                int newFlag = 1;
                int cnt = 0;
                int saveCnt = 0;
                for(int j = 0; j < 5; j++) {
                    if(j == 0) continue;
                    if((newTemp[j-1] +1) != newTemp[j]) {
                        newFlag = 0;
                        if(newTemp[j-1] == newTemp[j]) cnt++;
                        else cnt = 0;
                    }
                    if(cnt > saveCnt) saveCnt = cnt;
                }
                // 만약 카드 숫자가 연달아 뽑혔다면 newFlag는 1
                if(newFlag == 1) {
                    if (newTemp[0] == 10) score = 120; // 마운틴
                    else if (newTemp[0] == 1) score = 100; // 백스트레이트
                    else score = 80; // 스트레이트
                }
                else {
                    if(saveCnt == 3) score = 180; // 포카드
                    else if(saveCnt == 2) {
                        if(newTemp[0] == newTemp[1] || newTemp[3] == newTemp[4]) score = 160; // 풀하우스(같은숫자 3장 + 같은숫자 2장)
                        else score = 60; // 트리플
                    }
                    else if(saveCnt == 1) {
                        if( ( newTemp[0] == newTemp[1] && (newTemp[2] == newTemp[3] || newTemp[3] == newTemp[4]) ) || (newTemp[1] == newTemp[2] && newTemp[3] == newTemp[4]) )
                            score = 40; // 투페어
                        else score = 20; // 원페어
                    }
                }
            }
            if(newTemp[0] == 1) score += 14;
            else score += newTemp[4]; // 가장 큰 숫자의 점수 더함
            if(highest < score) highest = score;
        }
        return highest;
    }

    public int judge(Game game) {
        int winner = 0;
        int highest = 0;
        // 오픈카드의 모든 조합인덱스 생성 -- Compare함수에서 탐색하며 최상의 결과를 찾고 해당 인덱스의 카드배열을 bestSet으로 저장
        Vector<Vector<Integer>> collections = jokbo(game.getOpenCards(), 0, new int[5], new Vector<Integer>(3), new Vector<Vector<Integer>>());
        int[] playerBestResult = new int[game.getParticipants().size()];
        Card[][] playerCards = game.getPlayerCards();
        // for문 돌며 각 플레이어의 bestSet찾아서 저장
        for(int i = 0; i < game.getParticipants().size(); i++) {
            playerBestResult[i] = getBestCard(collections, game.getOpenCards(), playerCards[i]);
            System.out.println("playerBestResult = " + playerBestResult[i]);
        }
        for(int i = 0; i < game.getParticipants().size(); i++) {
            if(i == 0) highest = playerBestResult[i];
            else {
                if(highest < playerBestResult[i]) {
                    winner = i;
                    highest = playerBestResult[i];
                }
            }
        }
        // 우승 판정 -- 가진 카드와 오픈 카드를 조합하여 만들 수 있는 최상의 카드조합을 만든다 -> 참여인원의 최상의 카드조합을 비교하여 가장 우세한 사람이 승자!
        return winner;
    }

    public Vector<Vector<Integer>> jokbo(Card[] open, int idx, int[] visit, Vector<Integer> temp, Vector<Vector<Integer>> res) {
        if(idx == 3){
            // 오픈카드, 플레이어카드를 넘겨주고 temp에 저장한 이번에 뽑을 오픈카드와 플레이어카드를 조합한 결과 반환
            // 오픈카드를 조합할때마다 findBest로 넘겨서, 해당 조합을 findBest에서 기억하게함
            // findBest는 모든 조합을 확인하고 가장 좋은걸 리턴함?
            // jokbo 함수인자로 int[] res를 가지고 다니다가 idx 3 되면 findBest에 넣고 돌린다음에 결과를 반환하고,
            Vector<Integer> t = new Vector<Integer>();
            for(int i = 0; i < 3; i++) t.add(temp.get(i));
            res.add(t);
            return res;
        }
        for(int i = 0; i < 5; i++) {
            if(visit[i] == 0) {
                if(idx != 0 && temp.get(idx-1) > i) {
                    continue;
                }
                visit[i] = 1;
                temp.add(idx, i);
                jokbo(open, idx+1, visit, temp, res);
                visit[i] = 0;
                temp.remove(idx);
            }
        }
        return res;
    }
}
