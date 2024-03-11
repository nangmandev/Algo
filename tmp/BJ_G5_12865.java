/**

 @author 한규준
 @since 2023-09-07
 @see https://www.acmicpc.net/problem/12865
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_12865.java
 @youtube
 @performance 51248KB, 160ms
 @category DP 배낭문제
 @note

 2차원 배열을 만드는데
 한줄씩 진행해야 한다.
 -> 다같이 진행하는 경우, 상 하로 진행하는 경우, 상향/하향 등 모든 경우의 수 생각
 
 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_12865 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][2];
        int[][] dp = new int[N + 1][K + 1];

        // arr0 무게 1 가치
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j - arr[i][0] >= 0){
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - arr[i][0]] + arr[i][1]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
