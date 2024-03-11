/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_15652.java
 @youtube
 @performance 23916KB, 584ms
 @category 누적합
 @note

 오름차순 부분수열을 만들어야 하고
 중복 가능이므로
 부분수열이지만, 다음번 숫자가 이전 숫자보다 큰 경우를 구합니다.
 수열이 미리 정렬되어 있는 상태입니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class BJ_S3_15652 {
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

        permutation(0, new int[M]);
    }

    private static void permutation(int nth, int[] per) {
        if(nth == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(per[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i = 1; i <= N; i++) {
            if(nth == 0 || per[nth - 1] <= arr[i]) {
                per[nth] = arr[i];
                permutation(nth + 1, per);
            }
        }
    }
}
