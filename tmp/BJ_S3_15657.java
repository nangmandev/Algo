/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_15657.java
 @youtube
 @performance 18688KB, 276ms
 @category 백트래킹, BFS
 @note

 중복수열을 구하는데
 주어지는 수열 중 중복된 값이 없습니다.
 따라서 정렬 이후 visited없이 구합니다.
 오름차순으로 구하는데 중복이 가능하므로
 이전 자리 수열보다 같거나 큰 값만 탐색합니다.

 */
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class BJ_S3_15657 {
    static int[] arr;
    static int N;
    static int M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[N + 1];

        temp = br.readLine().split(" ");

        for(int i = 1; i <= N; i++) arr[i] = Integer.parseInt(temp[i - 1]);
        Arrays.sort(arr);

        permutation(0, new int[M]);
    }

    private static void permutation(int nth, int[] per) {
        StringBuilder sb = new StringBuilder();
        if(nth == M) {
            for(int i = 0; i < M; i++) {
                sb.append(per[i]).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(int i = 1; i < arr.length; i++) {
            if(nth == 0 || arr[i] >= per[nth - 1]) {
                per[nth] = arr[i];
                permutation(nth + 1, per);
            }
        }
    }
}
