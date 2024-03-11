/**

 @author 한규준
 @since 2023-08-01
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_11403.java
 @youtube
 @performance 15776KB, 140ms
 @category 플로이드-와셜
 @note

 1. 그래프 초기화
 2. 플로이드 와셜 수행
 3. 출력
 
 N 최대 100이므로, 100만회
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S1_11403 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++){
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < str.length; j++){
                arr[i][j] = Integer.parseInt(str[j]);
            }
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == 0 && arr[i][k] != 0 && arr[k][j] != 0){
                        arr[i][j] = 1;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
