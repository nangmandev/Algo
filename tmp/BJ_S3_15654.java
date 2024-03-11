/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_15654.java/
 @youtube
 @performance 57672KB, 1504ms
 @category 부분수열
 @note

중복되지 않은 수열을 구해야 하고
 사전 순서대로 출력해야 하므로
 배열을 정렬 후 visited를 이용해 부분수열을 구합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
public class BJ_S3_15654 {
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

        permutation(0, new int[M], new int[N + 1]);
    }

    private static void permutation(int nth, int[] per, int[] visited) {
        StringBuilder sb = new StringBuilder();
        if(nth == M) {
            for(int i = 0; i < M; i++) {
                sb.append(per[i]).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(int i = 1; i < arr.length; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                per[nth] = arr[i];
                permutation(nth + 1, per, visited);
                visited[i] = 0;
            }
        }
    }
}
