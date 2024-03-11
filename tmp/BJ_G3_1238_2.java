package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BJ_G3_1238_2 {
    static int N, M, X;
    static List<List<Rode>> rodes;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // M개의 단방향 도로, i번째 길을 지나는데 Ti시간 소모
        // 파티 참석을 위해 최단거리로 갔다가 돌아와야 한다
        // 가장 많은 시간을 소모한 학생의 소요시간은?

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        X = Integer.parseInt(str[2]);

        // N명의 학생, M개의 도로, X는 도착점
        rodes = new ArrayList<>();

        for(int i = 0; i <= N; i++){
            rodes.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            int cost = Integer.parseInt(str[2]);
            rodes.get(start).add(new Rode(end, cost));
        }

        // 초기화 완료
        int max = 0;
        for(int i = 1; i <= N; i++){
            if(i != X){
                max = Math.max(max, dijkstra(i, X) + dijkstra(X, i));
            }
        }

        System.out.println(max);

    }

    public static int dijkstra(int start, int end){
        int[] distances = new int[N + 1];
        for(int i = 1; i <= N; i++){
            distances[i] = 1000000000;
        }
        distances[start] = 0;

        PriorityQueue<Rode> pq = new PriorityQueue<>();
        pq.offer(new Rode(start, 0));

        while(!pq.isEmpty()){
            Rode nowRode = pq.poll();
            if(distances[nowRode.dest] < nowRode.cost){
                continue;
            }

            for(int i = 0; i < rodes.get(nowRode.dest).size(); i++){
                Rode nextRode = rodes.get(nowRode.dest).get(i);
                if(distances[nextRode.dest] > nowRode.cost + nextRode.cost){
                    distances[nextRode.dest] = nowRode.cost + nextRode.cost;
                    pq.offer(new Rode(nextRode.dest, distances[nextRode.dest]));
                }
            }
        }

        return distances[end];
    }

    public static class Rode implements Comparable<Rode>{
        int dest;
        int cost;
        Rode(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Rode o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
