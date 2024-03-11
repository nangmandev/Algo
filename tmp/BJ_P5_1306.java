/**

 @author 한규준
 @since 2023-09-23
 @see https://www.acmicpc.net/problem/1306
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_1306.java
 @youtube
 @performance 256424KB, 1028ms
 @category 모노톤 큐
 @note

2M - 1이 범위이므로
 2M - 1부터 끝까지 최대값을 확인

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_P5_1306 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int range = 2 * M - 1;
        Deque<int[]> deq = new ArrayDeque<>();
        int temp;
        for(int i = 1; i <= range; i++){
            temp = Integer.parseInt(st.nextToken());
            while(true) {
                if (!deq.isEmpty() && deq.peekLast()[0] < temp) {
                    deq.pollLast();
                }
                else break;
            }
            deq.offer(new int[]{temp, i});
        }
        bw.write(deq.peekFirst()[0] + " ");

        for(int i = range + 1; i <= N; i++){
            while(true) {
                if (!deq.isEmpty() && deq.peekFirst()[1] < i - range + 1) {
                    deq.poll();
                } else {
                    break;
                }
            }
            temp = Integer.parseInt(st.nextToken());
            while(true) {
                if (!deq.isEmpty() && deq.peekLast()[0] < temp) {
                    deq.pollLast();
                }
                else break;
            }
            deq.offer(new int[]{temp, i});
            bw.write(deq.peekFirst()[0] + " ");
        }

        bw.flush();
        bw.close();
    }
}
