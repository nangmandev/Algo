/**

 @author 한규준
 @since 2023-09-16
 @see https://www.acmicpc.net/problem/1647
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1647.java
 @youtube
 @performance 344224KB, 1604ms
 @category MST
 @note

 MST에서 최대비용 하나를 빼면
 최소비용을 가진 부분 MST 두개가 만들어진다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1647 {
    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;
        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] parent;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(from, to, cost));
            pq.offer(new Edge(to, from, cost));
        }

        int count = 0;
        int result = 0;
        int maxCost = 0;

        while(!pq.isEmpty()){
            if(count == N - 1) break;

            Edge nowEdge = pq.poll();
            if(union(nowEdge.from, nowEdge.to)){
                count++;
                result += nowEdge.cost;
                maxCost = Math.max(maxCost, nowEdge.cost);
            }
        }

        System.out.println(result - maxCost);
    }

    private static int find(int a){
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return false;
        if(pA > pB) {
            parent[pA] = pB;
        }
        else parent[pB] = pA;
        return true;
    }
}
