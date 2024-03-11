/**

 @author 한규준
 @since 2023-09-23
 @see https://www.acmicpc.net/problem/1806
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1806.java
 @youtube
 @performance
 @category 부분합
 @note

 연속하는 수열의 부분합이므로
 계속 진행하면서 꼬리자르기

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_1806 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        int N, S;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Integer> deq = new ArrayDeque<>();

        int temp;
        for(int i = 0; i < N; i++){
            temp = Integer.parseInt(st.nextToken());
            deq.offer(temp);
            sum += temp;
            while(true) {
                if (sum < S) break;
                minLength = Math.min(minLength, deq.size());
                sum -= deq.poll();
            }
        }

        if(minLength == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLength);
    }
}