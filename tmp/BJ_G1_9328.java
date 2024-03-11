package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G1_9328 {
    static class NowYXK{
        int y;
        int x;
        int key;
        public NowYXK(int y, int x, int key){
            this.y = y;
            this.x = x;
            this.key = key;
        }
    }

    static int[] movY = {-1, 1, 0, 0};
    static int[] movX=  {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int H, W;
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        int[][][] visited = new int[H][W][1];
        for(int i = 0; i < H; i++){
            String temp = br.readLine();
            for(int j = 0; j < W; j++){
                map[i][j] = temp.charAt(j);
            }
        }

        String defaultKey = br.readLine();
        int keys = 0;
        if(!defaultKey.equals("0")) {
            for (int i = 0; i < defaultKey.length(); i++) {
                keys += 1 << (defaultKey.charAt(i) - 'a');
            }
        }

        Deque<NowYXK> deq = new ArrayDeque<>();

        for(int i = 0; i < W; i++){
            if(map[0][i] != '*') deq.offer(new NowYXK(0, i, keys));
            if(map[H - 1][i] != '*') deq.offer(new NowYXK(H - 1, i, keys));
        }
        for(int i = 0; i < H; i++){
            if(map[i][0] != '*') deq.offer(new NowYXK(i, 0, keys));
            if(map[i][W - 1] != '*') deq.offer(new NowYXK(i, W - 1, keys));
        }

        int result = 0;
        while(!deq.isEmpty()){
            NowYXK nowXYK = deq.poll();
            if(map[nowXYK.y][nowXYK.x] == '$'){
                result++;
                map[nowXYK.y][nowXYK.x] = '.';
            }
            else if(map[nowXYK.y][nowXYK.x] >= 'A' && map[nowXYK.y][nowXYK.x] <= 'Z'){
                if((nowXYK.key & map[nowXYK.y][nowXYK.x]) != map[nowXYK.y][nowXYK.x]) continue;
            }
            else if(map[nowXYK.y][nowXYK.x] != '.'){
                nowXYK.key += 1 << map[nowXYK.y][nowXYK.x];
            }

            if((visited[nowXYK.y][nowXYK.x][0] & nowXYK.key) == nowXYK.key){
                continue;
            }
            else visited[nowXYK.y][nowXYK.x][0] += nowXYK.key;

            for(int i = 0; i < 4; i++){
                int nextY = nowXYK.y + movY[i];
                int nextX = nowXYK.x + movX[i];
                if(nextY >= 0 && nextY < H
                && nextX >= 0 && nextX < W
                && map[nextY][nextX] != '*'){
                    deq.offer(new NowYXK(nextY, nextX, nowXYK.key));
                }
            }
        }

        System.out.println(result);
    }
}
