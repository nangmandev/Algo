/**

 @author 한규준
 @since 2023-08-20
 @see https://www.acmicpc.net/problem/1197
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1197.java
 @youtube
 @performance 50588KB, 612ms
 @category Union-Find, 크루스칼
 @note

 간선 순서대로 돌면서 Union-Find를 진행하며 선택
 -> 같은 부모노드를 공유하면 같은 집합이므로, 간선을 선택할 필요가 없습니다.
 1. 우선순위 큐를 이용해 가중치 순서대로 간선 정렬
 2. 하나씩 간선을 뽑으면서, 부모노드를 탐색하여 간선 선택
 3. 총 길이 출력
 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1197 {
    static int V, E, nodes[];
    static long count;
    static PriorityQueue<edge> edges;

    static class edge implements Comparable{
        int a;
        int b;
        long weight;
        public edge(int a, int b, long weight){
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            edge temp = (edge) o;
            return Long.compare(this.weight, temp.weight);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        count = 0;

        nodes = new int[V];
        edges = new PriorityQueue<>();

        for(int i = 0; i < V; i++){
            nodes[i] = i;
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            edges.offer(new edge(a - 1, b - 1, c));
        }
        // 초기화 끝

        int i = 0;
        // 큐가 빌때까지 진행
        while(!edges.isEmpty()){
            // 가중치가 가장 작은 간선을 하나씩 뽑아 확인한다.
            edge nowEdge = edges.poll();
            // a와 b가 서로 부모노드가 다르면
            if(find(nowEdge.a) != find(nowEdge.b)){
                // 간선 선택, 부모노드 갱신
                Union(nowEdge.a, nowEdge.b);
                count += nowEdge.weight;
            }
        }
        System.out.println(count);
    }

    public static void Union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA < parentB){
            nodes[find(b)] = parentA;
        }
        else nodes[find(a)] = parentB;
    }

    public static int find(int idx){
        if(nodes[idx] == idx) return idx;
        return find(nodes[idx]);
    }
}
