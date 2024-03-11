/**

 @author 한규준
 @since 2023-08-12
 @see https://www.acmicpc.net/problem/1937
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1937.java
 @youtube
 @performance 55964KB, 464ms
 @category DFS, 메모이제이션
 @note
 
 1. 일단 모든 좌표마다 DFS를 구현
 2. 재귀함수는 선언부(맨처음실행) -> 중간 재귀호출부(전부 실행된 뒤) -> 마지막 으로 진행된다.
 3. 따라서, 마지막에 메모이제이션을 수행하면 마지막 호출된 DFS가 0, 그 다음이 1... 으로 진행되기 때문에
 4. 이 성질을 이용해서 거꾸로 메모이제이션을 수행한다.
 5. 방문하지 않은 곳은 DFS를 진행하되, 이미 방문한 곳은 그대로 출력하고 끝

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G3_1937 {
    static int[][] arr, visited;
    static int maxValue = 0, n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화 완료

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                maxValue = Math.max(maxValue, DFS(i, j));
            }
        }
        bw.write(maxValue + "\n");
        bw.flush();
        bw.close();
    }

    public static int DFS(int y, int x){
        if(visited[y][x] != 0) return visited[y][x];

        int temp = 0;

        int[] movX = {1, 0, -1, 0};
        int[] movY = {0, 1, 0, -1};
        for(int i = 0; i < 4; i++){
            int nextY = y + movY[i];
            int nextX = x + movX[i];

            if(nextY >= 0 && nextY < n
            && nextX >= 0 && nextX < n
            && arr[nextY][nextX] > arr[y][x]){
                temp = Math.max(temp, DFS(nextY, nextX));
            }
        }

        visited[y][x] = temp + 1;
        maxValue = Math.max(maxValue, visited[y][x]);

        return visited[y][x];
    }
}
