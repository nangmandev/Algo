/**

 @author 한규준
 @since 2023-08-01
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_11404.java
 @youtube
 @performance 59008KB, 528ms
 @category 플로이드-와셜
 @note

 1. 버스 경로와 비용 받기(중복경로 있으므로 최소비용 고려)
 2. 플로이드 와셜로 모든 거리 구하기
 3. 출력

 유저 수 N이 100이므로, N^3 = 100 * 100 * 100 = 1000000.
 경로 10만개이므로 입력주의

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_G4_11404 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for(int i = 0; i < m; i++){
            String[] temp = br.readLine().split(" ");
            int a = Integer.parseInt(temp[0]), b = Integer.parseInt(temp[1]), c = Integer.parseInt(temp[2]);
            if(c < arr[a - 1][b - 1] || arr[a - 1][b - 1] == 0) arr[a - 1][b - 1] = c;
        }

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(i == j) continue;
                    else if(arr[i][j] == 0 && arr[i][k] != 0 && arr[k][j] != 0){
                        arr[i][j] = arr[i][k] + arr[k][j];
                    } else if(arr[i][j] != 0 && arr[i][k] != 0 && arr[k][j] != 0){
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
