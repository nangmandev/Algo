package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BJ_G5_15681 {
    static int N, R, Q;
    static List<List<Integer>> routes;
    static int[] counts;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        R = Integer.parseInt(tmp[1]);
        Q = Integer.parseInt(tmp[2]);

        routes = new ArrayList<>();
        counts = new int[N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++){
            routes.add(new ArrayList<>());
            counts[i] = 1;
        }

        for(int i = 0; i < N - 1; i++){
            tmp = br.readLine().split(" ");
            int a = Integer.parseInt(tmp[0]);
            int b = Integer.parseInt(tmp[1]);
            routes.get(a).add(b);
            routes.get(b).add(a);
        }

        visited[R] = true;
        DFS(R);

        for(int i = 0; i < Q; i++){
            sb.append(counts[Integer.parseInt(br.readLine())] + "\n");
        }

        System.out.print(sb);
    }

    public static void DFS(int idx){
        List<Integer> childs = routes.get(idx);

        for(int i = 0; i < childs.size(); i++){
            if(!visited[childs.get(i)]) {
                visited[childs.get(i)] = true;
                DFS(childs.get(i));
                counts[idx] += counts[childs.get(i)];
            }
        }
    }
}
