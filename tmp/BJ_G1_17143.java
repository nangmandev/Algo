package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_G1_17143 {
    static int R, C, M;

    static public class Shark{
        int y;
        int x;
        int speed;
        int direction;
        int size;
        public Shark(int y, int x, int speed, int direction, int size){
            this.y = y;
            this.x = x;
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    static Map<Integer, Shark> sharks;
    static Map<Integer, Shark> tempSharks;
    static int result = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new HashMap<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            // 받고 자세 및 속도교정
            
            if (d == 3 || d == 4)
                s = s % (2 * (C - 1));
            else if(d == 1 || d == 2)
                s = s % (2 * (R - 1));
            
            sharks.put(y * 10000 + x, new Shark(y, x, s, d, z));
        }

        // 초기화 완료
        // 1초마다 낚시왕이 0 ~ C -1 까지 이동을 한다.
        // 이동을 한 번 할 때마다 해당 열에서 가장 가까운 상어를 잡는다
        // 상어가 이동한다.
        // 상어가 한 칸에 두 마리가 들어가게 되면 큰 상어만 살아남는다(이동을 마친 후 계산)
        // 상어가 경계에 걸리면 방향을 반대로 바꾼다.
        // 낚시왕이 이동을 마친 뒤 잡은 상어는 몇 마리인가

        // c = 낚시왕의 좌표
        for(int c = 1; c <= C; c++){
            catchShark(c);
            moveShark();
        }

        System.out.println(result);
    }

    // 상어를 잡는 함수
    private static void catchShark(int c){
        // ArrayList를 한 번 순회하며 체크
        int minkey = -1;
        int minVal = Integer.MAX_VALUE;
        for(int k : sharks.keySet()){
            if(k % 10000 == c && minVal > k / 10000){
                minVal = k / 10000;
                minkey = k;
            }
        }
        if(minkey == -1) return;
        else{
            result += sharks.get(minkey).size;
            sharks.remove(minkey);
        }
    }
    
    // 상어가 움직이는 함수
    // 1 위 2 아래 3 우 4 좌
    private static void moveShark(){
        tempSharks = new HashMap<>();
        for(int k : sharks.keySet()){
            Shark nowShark = sharks.get(k);

            // 위로 가는 경우
            int nowX = nowShark.x;
            int nowY = nowShark.y;
            int nowSpeed = nowShark.speed;
            int nowDirection = nowShark.direction;

            while(nowSpeed != 0){
                if(nowDirection == 1){
                    if(nowY == 1){
                        nowDirection = 2;
                        nowY++;
                    }
                    else {
                        nowY--;
                    }
                }
                else if(nowDirection == 2){
                    if(nowY == R){
                        nowDirection = 1;
                        nowY--;
                    }
                    else {
                        nowY++;
                    }
                }
                else if(nowDirection == 3){
                    if(nowX == C){
                        nowDirection = 4;
                        nowX--;
                    }
                    else {
                        nowX++;
                    }
                }
                else if(nowDirection == 4){
                    if(nowX == 1){
                        nowDirection = 3;
                        nowX++;
                    }
                    else {
                        nowX--;
                    }
                }
                nowSpeed--;
            }

            nowShark.y = nowY;
            nowShark.x = nowX;
            nowShark.direction = nowDirection;

            if(tempSharks.containsKey(nowY * 10000 + nowX)){
                if(tempSharks.get(nowY * 10000 + nowX).size > nowShark.size) continue;
                else tempSharks.put(nowY * 10000 + nowX, nowShark);
            }
            else {
                tempSharks.put(nowY * 10000 + nowX, nowShark);
            }
        }
        
        sharks = tempSharks;
    }
}
