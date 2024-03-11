/**

 @author 한규준
 @since 2024-01-23
 @see https://www.acmicpc.net/problem/2493
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_2493.java
 @youtube
 @performance 840ms, 171952KB
 @category 스택
 @note


 */

package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_G5_2493_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        String[] tmp = br.readLine().split(" ");

        for(int i = 0; i < N; i++){
            arr[i][0] = Integer.parseInt(tmp[i]);
            arr[i][1] = i + 1;
        }

        Stack<int[]> stack = new Stack();
        int[] result = new int[N + 1];

        for(int j = N - 1; j >= 0; j--){
            if(stack.empty()){
                stack.push(arr[j]);
            }
            else if(arr[j][0] < stack.peek()[0]){
                stack.push(arr[j]);
            }
            else {
                while(!stack.empty() && arr[j][0] >= stack.peek()[0]){
                    result[stack.pop()[1]] = arr[j][1];
                }
                stack.push(arr[j]);
            }
        }

        for(int i = 1; i < N + 1; i++){
            sb.append(result[i] + " ");
        }

        System.out.println(sb);
    }
}
