package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G4_11404_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];

        for(int i = 0; i < m; i++){
            String[] str = br.readLine().split(" ");
//            시작도시 a 도착도시 b 비용 c
            int start = Integer.parseInt(str[0]) - 1;
            int end = Integer.parseInt(str[1]) - 1;
            int cost = Integer.parseInt(str[2]);

            if(map[start][end] > cost || map[start][end] == 0){
                map[start][end] = cost;
            }
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(i == j) continue;
                    if(map[i][j] > map[i][k] + map[k][j] && (map[i][k] != 0 && map[k][j] != 0)) map[i][j] = map[i][k] + map[k][j];
                    else if(map[i][j] == 0 && (map[i][k] != 0 && map[k][j] != 0)) map[i][j] = map[i][k] + map[k][j];
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
