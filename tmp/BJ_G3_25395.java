package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_G3_25395 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] tmp = br.readLine().split(" ");
        int N = Integer.parseInt(tmp[0]);
        int S = Integer.parseInt(tmp[1]);

        int[] cars = new int[N + 1];
        int[] fuels = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        tmp = br.readLine().split(" ");
        for(int i = 1; i <= N; i++){
            cars[i] = Integer.parseInt(tmp[i - 1]);
        }

        tmp = br.readLine().split(" ");
        for(int i = 1; i <= N; i++){
            fuels[i] = Integer.parseInt(tmp[i - 1]);
        }

        Deque<Integer> deq = new ArrayDeque<>();

        deq.add(S);

        while(!deq.isEmpty()){
            int now = deq.poll();
            visited[now] = true;

            // 현 시점에서 갈 수 있는 위치 범위
            int minLocation = cars[now] - fuels[now];
            int maxLocation = cars[now] + fuels[now];

            // 오프셋
            int minOffset = 1;
            int maxOffset = 1;

            int nowPointer;
            while(true){
                nowPointer = now - minOffset++;

                if(nowPointer < 1 || cars[nowPointer] < minLocation) break;
                else if(cars[nowPointer] >= minLocation
                        && !visited[nowPointer]){
                    visited[nowPointer] = true;
                    deq.offer(nowPointer);
                }
            }

            while(true){
                nowPointer = now + maxOffset++;
                if(nowPointer > N || cars[nowPointer] > maxLocation) break;
                else if(cars[nowPointer] <= maxLocation
                        && !visited[nowPointer]){
                    visited[nowPointer] = true;
                    deq.offer(nowPointer);
                }
            }

        }

        for(int i = 1; i <= N; i++){
            if(visited[i]){
                sb.append(i + " ");
            }
        }

        System.out.println(sb);

    }
}
