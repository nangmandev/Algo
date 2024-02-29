package BOJ.divideandconquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S1_1992 {
    static int N;
    static int[][] map;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 왼쪽위 오른쪽위 왼쪽아래 오른쪽아래
        // 모두 0이면 0, 모두1이면 1, 0과 1이 섞여있으면 (0011)이런식으로 표현

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        // 초기화 완료
        divideAndConquer(0, 0, N);

        System.out.println(sb);
    }

    public static void divideAndConquer(int y, int x, int s){
        boolean isZero = true;
        boolean isOne = true;
        for(int i = y; i < y + s; i++){
            for(int j = x; j < x + s; j++){
                if(map[i][j] != 0) isZero = false;
                else if(map[i][j] != 1) isOne = false;
            }
        }

        if(isZero) {
            sb.append(0);
            return;
        }
        else if(isOne){
            sb.append(1);
            return;
        }

        if(s != 1) {
            sb.append("(");
            for (int i = y; i < y + s; i += s / 2) {
                for (int j = x; j < x + s; j += s / 2) {
                    divideAndConquer(i, j, s / 2);
                }
            }
            sb.append(")");
        }
    }
}
