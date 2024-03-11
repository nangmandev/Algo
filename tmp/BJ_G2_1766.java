/**

 @author 한규준
 @since 2023-09-17
 @see https://www.acmicpc.net/problem/1766
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_1766.java
 @youtube
 @performance 48124KB, 468ms
 @category 위상정렬, 우선순위큐
 @note

위상정렬을 하는데
 순서를 작은 순서대로 맞춰서 진행

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ_G2_1766 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            edge.add(new ArrayList<>());
        }

        int[] deg = new int[N + 1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edge.get(from).add(to);
            deg[to]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i <= N; i++){
            if(deg[i] == 0) pq.offer(i);
        }

        while(!pq.isEmpty()){
            int nowProblem = pq.poll();
            bw.write(nowProblem + " ");

            for(int i = 0; i < edge.get(nowProblem).size(); i++){
                int nextProblem = edge.get(nowProblem).get(i);
                if(--deg[nextProblem] == 0){
                    pq.offer(nextProblem);
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
