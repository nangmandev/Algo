package BOJ.topologicalsort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G4_2056 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수행할 작업 N개
        // 각 작업마다 걸리는 시간이 주어진다
        // 선행관계가 있어서, 먼저 완료되어야 하는 작업이 있다
        // K번 작업에 대해 선행 관계에 있는 작업의 번호는 1이상, K - 1이하이다
        // 선행관계에 있는 작업이 하나도 없는 작업이 반드시 하나 이상 존재한다.(1번 작업이 그러하다)
        // 모든 작업을 완료하기 위한 최소시간을 구하라.
        // 선행 관계가 없는 작업은 동시에 수행 가능하다.

        int N = Integer.parseInt(br.readLine());
        int[] degree = new int[N + 1];
        int[] cost = new int[N + 1];
        List<List<Integer>> vector = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        vector.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            vector.add(new ArrayList<>());
            String[] str = br.readLine().split(" ");
            cost[i] = Integer.parseInt(str[0]);
            for (int j = 2; j < Integer.parseInt(str[1]) + 2; j++) {
                vector.get(Integer.parseInt(str[j])).add(i);
                degree[i]++;
            }
        }

        Deque<Node> deq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                deq.offer(new Node(i, cost[i], 0));
                visited[i] = true;
            }
        }

        int count = 0;
        while (!deq.isEmpty()) {
            Node now = deq.poll();

            if (now.time != count) {
                count++;
            }

            if (now.cost == 0) {
                for (int i = 0; i < vector.get(now.idx).size(); i++) {
                    int idx = vector.get(now.idx).get(i);
                    if (--degree[idx] == 0 && !visited[idx]) {
                        deq.offer(new Node(idx, cost[idx] - 1, now.time + 1));
                        visited[idx] = true;
                    }
                }
            } else {
                now.cost--;
                now.time++;
                deq.offer(now);
            }
        }

        System.out.println(count);
    }

    static class Node {
        int idx;
        int cost;
        int time;

        Node(int idx, int cost, int time) {
            this.idx = idx;
            this.cost = cost;
            this.time = time;
        }
    }
}
