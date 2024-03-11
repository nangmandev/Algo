/**

 @author 한규준
 @since 2023-08-25
 @see https://www.acmicpc.net/problem/17472
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_17472.java
 @youtube
 @performance 11720KB, 88ms
 @category BFS, 재귀, 크루스칼, MST, 문제좀 읽자
 @note
 
 섬을 큰 덩이로 생각해도 되고
 섬 하나하나를 다 구해도 되고
 문제좀 제대로 읽기

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G1_17472 {
    static int N, M;
    static int[][] map;
    static int[] islands;
    static int level = 2;

    static int[] movY = {-1, 1, 0, 0};
    static int[] movX = {0, 0, -1, 1};

    static class Edge implements Comparable<Edge> {
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

    static PriorityQueue<Edge> edges = new PriorityQueue<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1) {
                    BFS(i, j);
                    level++;
                }
            }
        }

        islands = new int[level - 2];
        for(int i = 0; i < level - 2; i++){
            islands[i] = i;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] != 0) DFS(i, j);
            }
        }
        // 모든 간선이 추가됨

        int vtx = 0;
        int result = 0;
        while(!edges.isEmpty()){
            // 비용이 적은 간선부터 가져와서
            Edge nowEdge = edges.poll();
            if(union(nowEdge.start, nowEdge.end)){
                result += nowEdge.cost;
                vtx++;
            }
            if(vtx == level - 3) break;
        }

        if(vtx == level - 3) System.out.println(result);
        else System.out.println(-1);
    }

    // 사방 탐색으로 모든 간선 구하기
    // 내맘대로 dfs
    private static void DFS(int y, int x){
        int start = map[y][x] - 2;
        for(int i = 0; i < 4; i++){
            int nextY = y + movY[i];
            int nextX = x + movX[i];
            int count = 0;
            while(nextY >= 0 && nextY < N
            && nextX >= 0 && nextX < M
            && map[nextY][nextX] == 0){
                count++;
                nextY += movY[i];
                nextX += movX[i];
            }
            // 범위를 벗어나지 않고 바다면 한방향으로 계속 전진
            // while문이 끝났는데 범위 안쪽이면 다른 땅이다.
            // 그러면 간선 추가
            if(nextY >= 0 && nextY < N && nextX >= 0 && nextX < M){
                if(map[nextY][nextX] != map[y][x] && count >= 2){
                    int end = map[nextY][nextX] - 2;
                    edges.offer(new Edge(start, end, count));
                }
            }
        }
    }

    // 사방 탐색으로 모든 땅 칠하기
    // 내맘대로bfs
    private static void BFS(int y, int x){
        if(y < 0 || x < 0 || y >= N || x >= M) return;
        if(map[y][x] == 1){
            map[y][x] = level;
        }
        else return;

        for(int i = 0; i < 4; i++){
            BFS(y + movY[i], x + movX[i]);
        }
    }

    private static int find(int a){
        if(islands[a] == a) return a;
        return islands[a] = find(islands[a]);
    }

    private static boolean union(int a, int b){
        int pA = find(a);
        int pB = find(b);
        if(pA == pB) return false;
        else islands[pB] = pA;
        return true;
    }
}
