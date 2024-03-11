/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_15663.java
 @youtube
 @performance 56760KB, 1508ms
 @category 부분수열
 @note

 이전값을 기억해주면서
 계속 탐색하면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BJ_S2_15663 {
    static int[] arr;
    static int N, M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        temp = br.readLine().split(" ");
        arr = new int[N + 1];

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(temp[i - 1]);
        }

        Arrays.sort(arr);

        // 초기화 끝

        permutation(0, new int[M], new int[N + 1]);
    }

    private static void permutation(int nth, int[] per, int[] visited){
        if(nth == M){
            for(int i = 0; i < per.length; i++){
                System.out.print(per[i] + " ");
            }
            System.out.println();
            return;
        }
        int before = -1;
        for(int i = 1; i <= N; i++){
            if(visited[i] == 0){
                if(before != arr[i]) {
                    visited[i] = 1;
                    per[nth] = arr[i];
                    before = arr[i];
                    permutation(nth + 1, per, visited);
                    visited[i] = 0;
                }
            }
        }
    }
}
