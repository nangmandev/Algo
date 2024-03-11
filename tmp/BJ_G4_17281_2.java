/**

 @author 한규준
 @since 2024-01-23
 @see https://www.acmicpc.net/problem/17281
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_17281.java
 @youtube
 @performance 624ms 21088KB
 @category 순열, 구현
 @note

 ⚾⚾⚾⚾⚾⚾⚾⚾⚾⚾
 문제읽기

 */

package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_G4_17281_2 {
    public static int inning;
    public static ArrayList<ArrayList<Integer>> scores;
    public static int maxScore = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inning = Integer.parseInt(br.readLine());
        scores = new ArrayList<>();

        for(int i = 0; i < inning; i++){
            scores.add(new ArrayList<>());
            String[] temp = br.readLine().split(" ");
            for(int j = 0; j < 9; j++){
                scores.get(i).add(Integer.parseInt(temp[j]));
            }
        }

        // 이닝, 점수 입력 완료

        perm(0, new int[9], new boolean[9]);

        System.out.println(maxScore);
    }

    public static void perm(int nth, int[] players, boolean[] visited){
        if(nth == 9){
            game(players);
            return;
        }

        if(nth == 3){
            visited[0] = true;
            perm(nth + 1, players, visited);
            return;
        }

        for(int i = 1; i < 9; i++){
            if(!visited[i]){
                players[nth] = i;
                visited[i] = true;
                perm(nth + 1, players, visited);
                visited[i] = false;
            }
        }
    }

    public static void game(int[] player){
        int turn = 0;
        int nowInning = 0;
        int nowOutCount = 0;
        int gameScore = 0;
        int hit;

        boolean[] base = new boolean[3];

        while(nowInning != inning){
            while(nowOutCount != 3){
                hit = scores.get(nowInning).get(player[turn]);
                if(hit >= 3){
                    for(int i = 0; i < 3; i++){
                        if(base[i]) {
                            gameScore++;
                            base[i] = false;
                        }
                    }
                    if(hit == 4) gameScore++;
                    else base[2] = true;
                }
                else if(hit == 2){
                    for(int i = 1; i < 3; i++){
                        if(base[i]) {
                            gameScore++;
                            base[i] = false;
                        }
                    }
                    if(base[0]) {
                        base[2] = true;
                        base[0] = false;
                    }
                    base[1] = true;
                }
                else if(hit == 1){
                    if(base[2]) {
                        gameScore++;
                        base[2] = false;
                    }
                    for(int i = 1; i >= 0; i--){
                        if(base[i]){
                            base[i] = false;
                            base[i + 1] = true;
                        }
                    }
                    base[0] = true;
                }
                else if(hit == 0){
                    nowOutCount++;
                }
                if(turn == 8) turn = 0;
                else turn++;
            }
            for(int i = 0; i < 3; i++) base[i] = false;
            nowInning++;
            nowOutCount = 0;
        }

        if(gameScore > maxScore){
            maxScore = gameScore;
        }
    }
}
