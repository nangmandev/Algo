/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_15666.java/
 @youtube
 @performance 13780KB, 208ms
 @category 부분수열
 @note

 중복된 수열을
사전 순서대로 출력해야 합니다.
 before로 중복케이스를 없애고
 크기비교를 같거나 작은으로 하여 중복선택을 가능하게 합니다.
 중복수열이므로 visited는 없습니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_S2_15666 {
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

        int before = -1;
        for(int i = 1; i < arr.length; i++) {
            if((nth == 0 || arr[i] >= per[nth - 1]) && arr[i] != before) {
                per[nth] = arr[i];
                before = arr[i];
                permutation(nth + 1, per);
            }
        }
    }
}
