/**

 @author 한규준
 @since 2023-09-09
 @see https://www.acmicpc.net/problem/14002
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_14002.java
 @youtube
 @performance 12564KB, 112ms
 @category DP
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_G4_14002 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][2] = -1;
        }

        int maxVal = 0;
        int maxIdx = 0;

        for(int i = 0; i < N; i++){
            for(int j = i + 1; j < N; j++){
                if(arr[i][0] < arr[j][0] && arr[j][1] < arr[i][1] + 1){
                    arr[j][1] = arr[i][1] + 1;
                    arr[j][2] = i;
                    if(maxVal < arr[j][1]) {
                        maxIdx = j;
                        maxVal = Math.max(maxVal, arr[j][1]);
                    }
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        int idx = maxIdx;
        while(idx != -1){
            stack.push(arr[idx][0]);
            idx = arr[idx][2];
        }

        System.out.println(maxVal + 1);
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
