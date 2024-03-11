/**

 @author 한규준
 @since 2023-08-25
 @see https://www.acmicpc.net/problem/12100
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_12100.java
 @youtube
 @performance 20684KB, 216ms
 @category 구현, 재귀
 @note

 1. DFS를 4방향으로 한다.
 2. 중복 변환 조심

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_12100 {
    static int N;
    static int result = 0;
    static int[] movY = {1, -1, 0, 0};
    static int[] movX = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기화 완료, 4방향으로 넘긴다.
        for(int i = 0; i < 4; i++){
            DFS(0, i, arr);
        }

        System.out.println(result);
    }

    private static void DFS(int level, int direction, int[][] arr){
        if(level == 10){
            int tempRes = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    tempRes = Math.max(tempRes, arr[i][j]);
                }
            }
            result = Math.max(result, tempRes);
            return;
        }
        // 5회 재귀하면 끝

        int[][] newArr = new int[N][N];
        // 새 배열을 만들어서 비교한다.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                newArr[i][j] = arr[i][j];
            }
        }

        moveArr(direction, newArr);
        // 이 함수에서 움직인다.

        // 위 아래 오른쪽 왼쪽 4방향으로 넘긴다.
        for(int i = 0; i < 4; i++){
            DFS(level + 1, i, newArr);
        }
    }

    private static void moveArr(int direction, int[][] arr){
        // 모든 arr의 원소에 대해서 진행
        // 0이면 아래로 민다. -> 아래에서 위로 탐색
        int[][] visited = new int[N][N];
        if(direction == 0){
            for(int i = N - 1; i >= 0; i--){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == 0) continue;
                    int nextY = i;
                    while(true){
                        if(nextY == N - 1){
                            int temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[nextY][j] = temp;
                            break;
                        }
                        else if(arr[nextY + 1][j] != 0){
                            if(arr[nextY + 1][j] == arr[i][j]
                            && visited[nextY + 1][j] == 0){
                                arr[i][j] = 0;
                                arr[nextY + 1][j] = arr[nextY + 1][j] * 2;
                                visited[nextY + 1][j] = 1;
                                break;
                            }
                            else {
                                int temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[nextY][j] = temp;
                                break;
                            }
                        }
                        nextY += movY[direction];
                    }
                }
            }
        }
        // 1이면 위로 민다. -> 위에서 아래로 탐색
        else if(direction == 1){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == 0) continue;
                    int nextY = i;
                    while(true){
                        if(nextY == 0){
                            int temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[nextY][j] = temp;
                            break;
                        }
                        else if(arr[nextY - 1][j] != 0){
                            if(arr[nextY - 1][j] == arr[i][j]
                            && visited[nextY - 1][j] == 0){
                                arr[i][j] = 0;
                                arr[nextY - 1][j] *= 2;
                                visited[nextY - 1][j] = 1;
                                break;
                            }
                            else {
                                int temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[nextY][j] = temp;
                                break;
                            }
                        }
                        nextY += movY[direction];
                    }
                }
            }
        }
        // 2면 오른쪽으로 민다. -> 오른쪽에서 왼쪽으로 탐색
        else if(direction == 2){
            for(int j = N - 1; j >= 0; j--){
                for(int i = 0; i < N; i++){
                    if(arr[i][j] == 0) continue;
                    int nextX = j;
                    while(true){
                        if(nextX == N - 1){
                            int temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[i][nextX] = temp;
                            break;
                        }
                        else if(arr[i][nextX + 1] != 0){
                            if(arr[i][nextX + 1] == arr[i][j]
                            && visited[i][nextX + 1] == 0){
                                arr[i][j] = 0;
                                arr[i][nextX + 1] *= 2;
                                visited[i][nextX + 1] = 1;
                                break;
                            }
                            else {
                                int temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[i][nextX] = temp;
                                break;
                            }
                        }
                        nextX += movX[direction];
                    }
                }
            }
        }
        // 3이면 왼쪽으로 민다 -> 왼쪽에서 오른쪽으로 탐색
        else if(direction == 3){
            for(int j = 0; j < N; j++){
                for(int i = 0; i < N; i++){
                    if(arr[i][j] == 0) continue;
                    int nextX = j;
                    while(true){
                        if(nextX == 0){
                            int temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[i][nextX] = temp;
                            break;
                        }
                        else if(arr[i][nextX - 1] != 0){
                            if(arr[i][nextX - 1] == arr[i][j]
                            && visited[i][nextX - 1] == 0){
                                arr[i][j] = 0;
                                arr[i][nextX - 1] *= 2;
                                visited[i][nextX - 1] = 1;
                                break;
                            }
                            else {
                                int temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[i][nextX] = temp;
                                break;
                            }
                        }
                        nextX += movX[direction];
                    }
                }
            }
        }

//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }
}
