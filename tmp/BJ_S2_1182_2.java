/**

 @author 한규준
 @since 2024-01-23
 @see https://www.acmicpc.net/problem/1182
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_1182.java
 @youtube
 @performance
 @category
 @note


 */
package src.algo;

import com.sun.jdi.event.StepEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S2_1182_2 {
    public static int N, S;
    public static long count;
    public static int[] nums;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] temp = br.readLine().split(" ");
        String[] arr = br.readLine().split(" ");

        N = Integer.parseInt(temp[0]);
        S = Integer.parseInt(temp[1]);
        count = 0;
        nums = new int[N];

        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(arr[i]);
        }

        for(int i = 1; i <= N; i++){
            com(0, new int[i], 0);
        }

        System.out.println(count);
    }

    public static void com(int nth, int[] arr, int start){
        if(nth == arr.length){
            long tmpCount = 0;
            for(int i = 0; i < arr.length; i++){
                tmpCount += arr[i];
            }
            if(tmpCount == S){
                count++;
            }
            return;
        }

        for(int i = start; i < N; i++){
            arr[nth] = nums[i];
            com(nth + 1, arr, i + 1);
        }
    }
}
