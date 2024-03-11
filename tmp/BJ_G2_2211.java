/**

 @author 한규준
 @since 2023-08-20
 @see https://www.acmicpc.net/problem/2211
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_2211.java
 @youtube
 @performance 91792KB, 540ms
 @category 다익스트라
 @note

1. 노드별로 간선을 저장하고
 2. 시작 노드부터 모든 간선을 탐색
 3. 다음 노드를 꺼내서 계속 비교하면서 갱신
 4. 갱신되었으면 distance가 변경되었으므로
 5. 모두 갱신이 완료되면 distance를 확인하여 출력합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G2_2211 {
    static final int INF = 987654321;
    static int N, M;
    static int[][] distance;
    static int count = 0;

    static class edge{
        int idx;
        int cost;
        public edge(int idx, int cost){
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        ArrayList<ArrayList<edge>> nodes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1][2];
        for(int i = 0; i <= N; i++){
            nodes.add(new ArrayList<>());
            distance[i][0] = INF;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            nodes.get(a).add(new edge(b, c));
            nodes.get(b).add(new edge(a, c));
        }

        Deque<edge> edges = new ArrayDeque<>();
        edges.offer(new edge(1, 0));
        distance[1][0] = 0;

        while(!edges.isEmpty()){
            edge nowEdge = edges.poll();
            if(distance[nowEdge.idx][0] < nowEdge.cost){
                continue;
            }

            for(int i = 0; i < nodes.get(nowEdge.idx).size(); i++){
                edge nextEdge = nodes.get(nowEdge.idx).get(i);
                if(distance[nextEdge.idx][0] > nowEdge.cost + nextEdge.cost){
                    distance[nextEdge.idx][0] = nowEdge.cost + nextEdge.cost;
                    distance[nextEdge.idx][1] = nowEdge.idx;
                    edges.add(new edge(nextEdge.idx, distance[nextEdge.idx][0]));
                }
            }
        }

        for(int i = 2; i <= N; i++){
            if(distance[i][0] != INF){
                count++;
            }
        }

        bw.write(count + "\n");

        for(int i = 2; i <= N; i++){
            if(distance[i][0] != INF){
                bw.write(i + " " + distance[i][1] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
