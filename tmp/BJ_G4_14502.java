/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_14502.java
 @youtube
 @performance 295456KB, 2092ms
 @category 백트래킹, BFS
 @note

처음에 6중포문으로 하려했는데
 백트래킹으로 푸는법을 발견해버렸다

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_14502 {
    static int maxCount = 0;
    private static void BFS(int[][] arr, int N, int M){
        Deque<int[]> deque = new ArrayDeque<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr[i][j] == 2) deque.add(new int[]{i, j});
            }
        }

        int[][] arr2 = new int[N][M];
        for(int i = 0; i < N; i++) arr2[i] = arr[i].clone();

        int[] nextX = {1, 0, -1, 0};
        int[] nextY = {0, 1, 0, -1};
        while(deque.size() != 0){
            int[] yx = deque.pollFirst();
            arr2[yx[0]][yx[1]] = 2;
            for(int i = 0; i < 4; i++){
                int ny = nextY[i] + yx[0];
                int nx = nextX[i] + yx[1];
                if(ny >= 0 && ny < N && nx >= 0 && nx < M && arr2[ny][nx] == 0){
                    deque.add(new int[]{ny, nx});
                }
            }
        }

        int tempCount = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr2[i][j] == 0) tempCount++;
            }
        }

        if(tempCount > maxCount) {
            maxCount = tempCount;
        }
    }

    static void count(int[][]arr, int N, int M, int wallcount){
        if(wallcount == 3){
            BFS(arr, N, M);
            return;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr[i][j] == 0){
                    arr[i][j] = 1;
                    count(arr, N, M, wallcount + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count(arr, N, M, 0);

        System.out.println(maxCount);
    }
}
