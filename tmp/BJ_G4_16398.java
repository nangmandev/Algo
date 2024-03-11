/**

 @author 한규준
 @since 2023-08-29
 @see https://www.acmicpc.net/problem/16398
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_16398.java
 @youtube
 @performance 173540KB, 992ms
 @category 최소 스패닝 트리, 크루스칼 알고리즘
 @note

인접 행렬로 주어지는 경로를 받은 다음
 우선순위 큐에 cost를 기준으로 넣고
 하나씩 빼면서 연결되었는지 확인
 연결되었으면 카운트 더하고 비용을 더하고
 카운트가 행성 수 - 1이면 탐색 종료

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_16398 {
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int cost;
        public Edge(int start, int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        for(int i = 0; i <= N; i++){
            arr[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                pq.offer(new Edge(i + 1, j + 1, Integer.parseInt(st.nextToken())));
            }
        }

        long result = 0;
        int count = 0;
        while(!pq.isEmpty()){
            Edge nowEdge = pq.poll();
            if(count == N - 1) break;

            if(union(nowEdge.start, nowEdge.end)){
                result += nowEdge.cost;
                count++;
            }
        }

        System.out.println(result);
    }

    private static int find(int a){
        if(arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }

    private static boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return false;
        arr[pB] = pA;
        return true;
    }
}
