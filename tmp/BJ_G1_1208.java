/**

 @author 한규준
 @since 2023-09-17
 @see https://www.acmicpc.net/problem/1208
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_1208.java
 @youtube
 @performance 35892KB, 204ms
 @category 이분탐색
 @note

부분집합을 반으로 나눠서
 첫번째 부분집합의 합 개수를 저장
 두번째 부분집합은 합 하나씩 구할때마다 원래 구했던 것과 합이 S면 더해준다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G1_1208 {
    static int N, S;
    static int[] arr1, arr2;
    static int[] sum1;

    static long result;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr1 = new int[N / 2];
        if(N % 2 == 1){
            arr2 = new int[N / 2 + 1];
        } else arr2 = new int[N / 2];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N / 2; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = N / 2; i < N; i++){
            arr2[i - N / 2] = Integer.parseInt(st.nextToken());
        }

        result = 0;
        sum1 = new int[6000001];

        powerSet1(0, new int[arr1.length]);
        powerSet2(0, new int[arr2.length]);

        if(S == 0) System.out.println(result - 1);
        else System.out.println(result);
    }

    private static void powerSet1(int nth, int[] tempArr){
        if(nth == tempArr.length){
            int sum = 0;
            for(int i = 0; i < nth; i++){
                if(tempArr[i] != 0) sum += tempArr[i];
            }

            if(sum >= -2000000 && sum <= 2000000){
                sum1[sum + 2000000]++;
            }
            return;
        }

        tempArr[nth] = arr1[nth];
        powerSet1(nth + 1, tempArr);
        tempArr[nth] = 0;
        powerSet1(nth + 1, tempArr);
    }

    private static void powerSet2(int nth, int[] tempArr){
        if(nth == tempArr.length){
            int sum = 0;
            for(int i = 0; i < nth; i++){
                if(tempArr[i] != 0) sum += tempArr[i];
            }

            if(sum >= -2000000 && sum <= 2000000){
                result += sum1[S - sum + 2000000];
            }
            return;
        }

        tempArr[nth] = arr2[nth];
        powerSet2(nth + 1, tempArr);
        tempArr[nth] = 0;
        powerSet2(nth + 1, tempArr);
    }
}
