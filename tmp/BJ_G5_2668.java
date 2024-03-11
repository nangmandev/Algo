package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ_G5_2668 {
    static int[] arr;
    static List<Integer> res;
    static boolean[] visited;
    static boolean[] fixed;
    static int first;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        arr = new int[Integer.parseInt(br.readLine()) + 1];

        for(int i = 1; i < arr.length; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        res = new ArrayList<>();
        visited = new boolean[arr.length];
        fixed = new boolean[arr.length];

        // 초기화 완료

        for(int i = 1; i < arr.length; i++){
            if(!fixed[i]) {
                first = i;
                for(int j = 0; j < arr.length; j++){
                    visited[j] = false;
                }
                DFS(i, new ArrayList<>());
            }
        }

        for(int i = 1; i < arr.length; i++){
            if(arr[i] == i) {
                res.add(i);
            }
        }

        Collections.sort(res);

        sb.append(res.size() + "\n");

        for(int i = 0; i < res.size(); i++){
            sb.append(res.get(i) + "\n");
        }

        System.out.println(sb);
    }

    // visited = 반복마다 체크
    // fixed = 확정된거 고정
    public static void DFS(int idx, List<Integer> tmpRes){
        tmpRes.add(idx);
        visited[idx] = true;
        // 처음에 들어오면 추가

        // 같은거나 고정된거면 끝
        if(arr[idx] == idx || fixed[idx]){
            return;
        }

        // 한바퀴 돌면 추가
        if(arr[idx] == first){
            for(int i = 0; i < tmpRes.size(); i++){
                res.add(tmpRes.get(i));
                fixed[tmpRes.get(i)] = true;
            }
            return;
        }

        // 방문하지 않았으면 진행
        if(!visited[arr[idx]] && !fixed[arr[idx]]){
            DFS(arr[idx], tmpRes);
        }
    }
}
