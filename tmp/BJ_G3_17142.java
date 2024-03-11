package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G3_17142 {
    static int N, M;
    static int[][] map, visited, tmp;
    static List<Virus> viruses;
    static int depth;
    static int result;

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 모든 바이러스는 처음에 비활성 상태이다.
        // 활성상태 바이러스는 상하좌루 인접한 모든 빈칸으로 동시에 복제되며 1초가 걸린다
        // 바이러스 M개를 활성상태로 변경하려 한다.
        // N*N정사각형이다.
        // 빈칸, 벽, 바이러스로 이뤄져 있다.
        // 활성 바이러스가 비활성 바이러스가 있는 칸으로 가면 비활서 바이러스가 활성으로 변한다.
        // 바이러스는 M이상 10이하로 주어진다. ( 1 <= M <= 10 )

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        map = new int[N][N];
        visited = new int[N][N];
        tmp = new int[N][N];
        viruses = new ArrayList<>();
        depth = 0;
        result = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++){
            str = br.readLine().split(" ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(str[j]);
                if(map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0, 0));
                }
            }
        }

        // 맵 초기화 완료
        // 활성 바이러스가 일단 정해지면, 비활성 바이러스는 빈공간이나 마찬가지
        // 활성 바이러스의 조합이 하나 정해지면 회차++ 하고 BFS를 진행한다.
        // 원본 맵, count 맵(몇초 걸렸는지), visited 맵(몇 회차인지) 3개의 맵을 만들어 진행
        // visited가 이번 회차이면 방문한 적이 있는것. 아니면 방문하지 않은것 -> 초 기록

        combi(0, 0, new int[M]);

        if(result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static void BFS(int[] arr){
        // 받은 바이러스 배열에 대해 탐색을 시작한다.

        int maxCount = 0;
        Deque<Virus> deque = new ArrayDeque<>();

        for(int num : arr){
            deque.offer(viruses.get(num));
            visited[viruses.get(num).y][viruses.get(num).x] = depth;
        }
        // 처음 바이러스들을 넣는다.

        // 큐가 빌때까지 진행
        while(!deque.isEmpty()){
            Virus now = deque.poll();
            maxCount = Math.max(maxCount, now.count);

            for(int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if(nextY < N && nextY >= 0
                && nextX < N && nextX >= 0
                && visited[nextY][nextX] != depth
                && map[nextY][nextX] != 1){
                    visited[nextY][nextX] = depth;
                    if(map[now.y][now.x] == 0 && map[nextY][nextX] == 2) {
                        deque.offer(new Virus(nextY, nextX, now.count, now.count + 1));
                    } else if(map[now.y][now.x] == 2 && map[nextY][nextX] == 2){
                        deque.offer(new Virus(nextY, nextX, now.count, now.hidden + 1));
                    } else if(map[now.y][now.x] == 2 && map[nextY][nextX] == 0){
                        deque.offer(new Virus(nextY, nextX, now.hidden + 1, 0));
                    } else {
                        deque.offer(new Virus(nextY, nextX, now.count + 1, 0));
                    }
                }
            }
        }

        // 한번 탐색이 끝나면 전부 바이러스가 되었는지 체크해야한다.
        int discovered = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j] == depth || map[i][j] == 1 || map[i][j] == 2){
                    discovered++;
                }
            }
        }

        if(discovered == N * N){
            result = Math.min(result, maxCount);
        }
    }

    static void combi(int nth, int start, int[] arr){
        if(nth == M){
            depth++;
            BFS(arr);
            return;
        }

        for(int k = start; k < viruses.size(); k++){
            arr[nth] = k;
            combi(nth + 1, k + 1, arr);
        }
    }

    static class Virus{
        int y;
        int x;
        int count;
        int hidden;
        Virus(int y, int x, int count, int hidden){
            this.y = y;
            this.x = x;
            this.count = count;
            this.hidden = hidden;
        }
    }
}
