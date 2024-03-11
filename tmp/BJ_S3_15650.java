/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_15650.java
 @youtube
 @performance 11532KB, 84ms
 @category 순열
 @note

 중복없이 오름차순 출력이므로
 정렬된 수열을 visited를 이용해야 하는데
 수열은 이미 정렬되어 있으므로
 해당 크기만큼의 부분수열을 만들면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BJ_S3_15650 {
    static int[] arr;
    static int N;
    static int M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[N + 1];

        for(int i = 1; i <= N; i++) arr[i] = i;

        permutation(0, new int[M], new int[N + 1]);
    }

    private static void permutation(int nth, int[] per, int[] visited) {
        if(nth == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(per[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 1; i <= N; i++) {
            if(visited[i] == 0 && (nth == 0 || per[nth - 1] < arr[i])) {
                per[nth] = arr[i];
                visited[i] = 1;
                permutation(nth + 1, per, visited);
                visited[i] = 0;
            }
        }
    }
}
