package BOJ.divideandconquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S2_1780 {
    static int N;
    static int[] count;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        count = new int[3];

        map = new int[N][N];

        String[] tmp;
        for(int i = 0; i < N; i++){
            tmp = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(tmp[j]);
                if(map[i][j] == -1) map[i][j] = 2;
            }
        }

        // 초기화 완료
        dvideAndConquer(0, 0, N, 0);
        dvideAndConquer(0, 0, N, 1);
        dvideAndConquer(0, 0, N, 2);

        System.out.println(count[2] + "\n" + count[0] + "\n" + count[1]);
    }

    public static void dvideAndConquer(int y, int x, int s, int idx){
        boolean check = true;
        for(int i = y; i < y + s; i++){
            for(int j = x; j < x + s; j++){
                if(map[i][j] != idx){
                    check = false;
                }
            }
        }

        if(check){
            count[idx]++;
        } else if(s != 1){
            for(int i = y; i < y + s; i += s / 3){
                for(int j = x; j < x + s; j += s / 3){
                    dvideAndConquer(i, j, s / 3, idx);
                }
            }
        }
    }
}
