/**

 @author 한규준
 @since 2023-09-10
 @see https://www.acmicpc.net/problem/12015
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_12015.java
 @youtube
 @performance 125316KB, 476ms
 @category DP, 이분탐색
 @note


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G2_12015 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 2;
        int[] res = new int[N + 1];
        res[1] = arr[1];

        for(int i = 2; i <= N; i++){
            if(arr[i] > res[idx - 1]){
                res[idx++] = arr[i];
            }
            else if(arr[i] < res[idx - 1]){
                int left = 1;
                int right = idx - 1;
                while(left < right){
                    int mid = (left + right) / 2;
                    if(res[mid] < arr[i]){
                        left = mid + 1;
                    }
                    else right = mid;
                }
                res[left] = arr[i];
            }
        }

        System.out.println(idx - 1);
    }
}
