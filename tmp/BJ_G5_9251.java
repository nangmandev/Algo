/**

 @author 한규준
 @since 2023-09-07
 @see https://www.acmicpc.net/problem/9251
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_9251.java
 @youtube
 @performance 16216KB, 116ms
 @category DP, LCS
 @note



 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G5_9251 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String st1 = br.readLine();
        String st2 = br.readLine();
        int N = st1.length();
        int K = st2.length();
        int[][] arr = new int[N + 1][K + 1];

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= K; j++){
                if(st1.charAt(i - 1) == st2.charAt(j - 1)){
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                }
                else{
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }

        System.out.println(arr[N][K]);
    }
}
