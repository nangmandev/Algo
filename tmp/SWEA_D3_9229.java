/**

 @author 한규준
 @since 2023-08-08
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D3_9229.java
 @youtube
 @performance 26912kb, 197ms
 @category 조합
 @note

 1. N개의 과자 봉지 중 2개의 조합을 고르는 문제입니다.
 2. 2개의 과자 봉지 무게의 총합이 M이하여야 하고, 그 중 가장 커야 합니다.
 3. 중복이 허용되지 않습니다.
 4. 순서는 중요하지 않으나, 이전에 이미 탐색한 부분이기 때문에 지금 고른 과자의 다음 것부터 탐색해도 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_9229 {
    static int N;
    static int M;
    static int[] arr;
    static int[] visited;
    static int maxValue;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= TC; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N];
            visited = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            // 초기화 완료

            maxValue = -1;
            combination(0, new int[2], 0);

            System.out.printf("#%d %d\n", tc, maxValue);
        }
    }

    private static void combination(int nth, int[] tempArr, int start){
        if(nth == 2){
            int tempSum = 0;
            for(int i = 0; i < 2; i++){
                tempSum += tempArr[i];
            }
            if(tempSum > maxValue && M >= tempSum) maxValue = tempSum;
            return;
        }
        // 조합이 만들어지면 합 확인후 리턴

        for(int i = start; i < arr.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                tempArr[nth] = arr[i];
                combination(nth + 1, tempArr, i);
                visited[i] = 0;
            }
        }
    }
}
