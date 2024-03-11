/**

 @author 한규준
 @since 2023-10-15
 @see https://www.acmicpc.net/problem/18405
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_18405.java
 @youtube
 @performance 17380KB, 152ms
 @category 조합, 최단경로, BFS
 @note

 푸는방법은 3가지 정도 나올 것 같다
 BFS로 하면 시간이 오래 걸릴 것 같다
 조합으로도 가능할 것 같음
 1. 해당 위치까지 도달하는 경우는 맨해튼 거리로 계산해야 한다.
 2. 바이러스별 최단거리를 계속 갱신한다.
 3. 최단거리가 같은 경우 바이러스 숫자를 우선순위로 갱신한다.
 4. S보다 최단거리가 크면 도달하지 못하므로 패스
 5. 아니면 출력한다.
 
 치킨배달의 악몽이 떠오르는 문제

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_18405 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] tmpMin = new int[2];
        // 0에는 최단거리
        // 1에는 해당 바이러스 저장
        tmpMin[0] = Integer.MAX_VALUE;
        tmpMin[1] = Integer.MAX_VALUE;

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                int tmp = map[i][j];
                if(tmp != 0) {
                    int distY = Math.abs(Y - i);
                    int distX = Math.abs(X - j);
                    int diff = distY + distX;
                    if (diff < tmpMin[0]) {
                        tmpMin[0] = diff;
                        tmpMin[1] = tmp;
                    } else if (diff == tmpMin[0] && tmp < tmpMin[1]) {
                        tmpMin[1] = tmp;
                    }
                }
            }
        }

        if(S < tmpMin[0]) System.out.println(0);
        else {
            System.out.println(tmpMin[1]);
        }
    }
}
