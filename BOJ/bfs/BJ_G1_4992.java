package BOJ.bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BJ_G1_4992 {
    static Node[] nodes;
    static List<List<Edge>> edges;
    static int min;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 깨끗한 칸과 더러운 칸이 있다
        // 더러운 칸을 방문해 깨끗한 칸으로
        // 가구가 놓인 칸으로 이동 불가
        // 인접한 칸으로 이동 가능, 같은 칸 여러번 방문 가능
        // 더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 이동횟수의 최솟값

        // .깨끗 *더럽 x가구 o시작
        while(true) {
            String[] str = br.readLine().split(" ");
            int W = Integer.parseInt(str[0]);
            int H = Integer.parseInt(str[1]);

            if (W == 0 && H == 0) break;

            char[][] arr = new char[H][W];

            int dirty = 1;
            min = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < W; j++) {
                    arr[i][j] = tmp.charAt(j);
                    if (arr[i][j] == 'o') {
                        arr[i][j] = '0';
                    } else if (arr[i][j] == '*') {
                        arr[i][j] = (char) (dirty + '0');
                        dirty++;
                    }
                }
            }

            nodes = new Node[dirty];
            int idx = 1;
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(arr[i][j] == '0') {
                        nodes[0] = new Node(i, j);
                    }
                    else if(arr[i][j] != 'x' && arr[i][j] != '.'){
                        nodes[idx++] = new Node(i, j);
                    }
                }
            }

            edges = new ArrayList<>();
            for (int i = 0; i < dirty; i++) {
                edges.add(new ArrayList<>());
            }

            int[] nY = {0, 0, -1, 1};
            int[] nX = {-1, 1, 0, 0};
            for (int i = 0; i < dirty; i++) {
                Deque<Node> deq = new ArrayDeque<>();
                deq.offer(new Node(nodes[i].y, nodes[i].x));
                boolean[][] visited = new boolean[H][W];
                visited[nodes[i].y][nodes[i].x] = true;

                while (!deq.isEmpty()) {
                    Node now = deq.poll();

                    for (int k = 0; k < 4; k++) {
                        int nextY = now.y + nY[k];
                        int nextX = now.x + nX[k];
                        if (nextY >= 0 && nextY < H
                                && nextX >= 0 && nextX < W
                                && !visited[nextY][nextX]
                                && arr[nextY][nextX] != 'x') {
                            Node next = new Node(nextY, nextX);
                            visited[nextY][nextX] = true;
                            next.count = now.count + 1;
                            if (arr[nextY][nextX] == '.') {
                                deq.offer(next);
                            } else {
                                edges.get(i).add(new Edge(arr[nextY][nextX] - '0', next.count));
                                deq.offer(next);
                            }
                        }
                    }
                }
            }

            permu(1, new int[dirty], new boolean[dirty]);

            if(edges.get(0).size() != dirty - 1) sb.append(-1 + "\n");
            else sb.append(min + "\n");
        }
        System.out.print(sb);
    }

    public static void permu(int nth, int[] arr, boolean[] visited){
        if(nth == arr.length){
            int tmpMin = 0;
            int start = 0, idx = 1, end = 0;
            while(idx != arr.length){
                end = arr[idx++];
                for(int i = 0; i < edges.get(start).size(); i++){
                    if(edges.get(start).get(i).dest == end){
                        tmpMin += edges.get(start).get(i).cost;
                        start = edges.get(start).get(i).dest;
                        break;
                    }
                }
                if(tmpMin > min) return;
            }
            min = Math.min(min, tmpMin);
            return;
        }

        for(int i = 1; i < arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[nth] = i;
                permu(nth + 1, arr, visited);
                visited[i] = false;
            }
        }
    }

    public static class Node {
        int y;
        int x;
        int count;

        Node(int y, int x) {
            this.y = y;
            this.x = x;
            this.count = 0;
        }
    }

    public static class Edge {
        int dest;
        int cost;

        Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
}
