/**

 @author 한규준
 @since 2023-09-13
 @see https://www.acmicpc.net/problem/1202
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_1202.java
 @youtube
 @performance 123968KB, 1660ms
 @category 우선순위큐, 포인터
 @note


 */
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G2_1202 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] je = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            je[i][0] = Integer.parseInt(st.nextToken());
            je[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] c = new int[K];
        for(int i = 0; i < K; i++){
            c[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(je, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        Arrays.sort(c);

        int idx = 0;
        long value = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        for(int i = 0; i < K; i++){
            while(idx < N){
                if(je[idx][0] > c[i]) break;
                pq.offer(je[idx]);
                idx++;
            }
            if(!pq.isEmpty()) value += pq.poll()[1];
        }

        System.out.println(value);
    }
}
