/**

 @author 한규준
 @since 2023-08-01
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1389.java
 @youtube
 @performance 12048KB, 96ms
 @category 플로이드-와셜
 @note

 1. 친구목록과 간선 받기
 2. 플로이드 와셜로 모든 거리 구하기
 3. 각 사람별 케빈 베이컨 수 구하기
 
 유저 수 N이 100이므로, N^3 = 100 * 100 * 100 = 1000000.
 시간 충분

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BJ_S1_1389 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);
        int[][] arr = new int[N][N];
        int sum = Integer.MAX_VALUE, lowMan = 0;

        for(int i = 0; i < M; i++){
            String[] temp = br.readLine().split(" ");
            int A = Integer.parseInt(temp[0]), B = Integer.parseInt(temp[1]);
            arr[A - 1][B - 1] = 1;
            arr[B - 1][A - 1] = 1;
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(i == j) continue;
                    else if(arr[i][j] == 0 && arr[i][k] != 0 && arr[k][j] != 0) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    } else if(arr[i][j] != 0 && arr[i][k] != 0 && arr[k][j] != 0){
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }
        }

        for(int i = 0; i < N; i++){
            int tempSum = 0;
            for(int j = 0; j < N; j++){
                tempSum += arr[i][j];
            }
            if(tempSum < sum) {
                sum = tempSum;
                lowMan = i + 1;
            }
        }

        bw.write(lowMan + "");

        bw.flush();
        bw.close();
    }
}