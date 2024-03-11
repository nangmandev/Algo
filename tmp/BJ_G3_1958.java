/**

 @author 한규준
 @since 2023-09-09
 @see https://www.acmicpc.net/problem/1958
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1958.java
 @youtube
 @performance 17556KB, 132ms
 @category DP
 @note

코드 잘읽어보기...

 */
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G3_1958 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String st1 = br.readLine();
        String st2 = br.readLine();
        String st3 = br.readLine();
        int N = st1.length();
        int M = st2.length();
        int K = st3.length();

        int[][][] arr = new int[N + 1][M + 1][K + 1];

        int max = 0;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                for(int k = 1; k <= K; k++){
                    if(st1.charAt(i - 1) == st2.charAt(j - 1) && st1.charAt(i - 1) == st3.charAt(k - 1)){
                        arr[i][j][k] = arr[i - 1][j - 1][k - 1] + 1;
                    }
                    else {
                        arr[i][j][k] = Math.max(Math.max(arr[i - 1][j][k], arr[i][j - 1][k]), arr[i][j][k - 1]);
                    }
                }
            }
        }

        System.out.println(arr[N][M][K]);
    }
}
