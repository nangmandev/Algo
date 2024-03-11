/**

 @author 한규준
 @since 2023-08-25
 @see https://www.acmicpc.net/problem/12094
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P4_12094.java
 @youtube
 @performance 298940KB, 4948ms
 @category 구현, 재귀, 가지치기
 @note

다시 확인할것

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_P4_12094 {
    static int N;
    static long result = 0;
    static long[] resLevel = new long[11];
    static int[] movY = {1, -1, 0, 0};
    static int[] movX = {0, 0, 1, -1};
    static long[][] visited;
    static boolean isChanged = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        long[][] arr = new long[N][N];
        visited = new long[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] > result) result = arr[i][j];
            }
        }

        // 초기화 완료, 4방향으로 넘긴다.
        for(int i = 0; i < 4; i++){
            DFS(0, i, arr);
        }

        System.out.println(result);

    }

    private static void DFS(int level, int direction, long[][] arr){
        long tempRes = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                tempRes = Math.max(tempRes, arr[i][j]);
            }
        }
        if(result < tempRes){
            result = tempRes;
        }
        if(resLevel[level] < tempRes) resLevel[level] = tempRes;
        if(level == 10 || resLevel[level] / 2 > tempRes){
            return;
        }

        long[][] newArr = new long[N][N];
        // 새 배열을 만들어서 비교한다.
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                newArr[i][j] = arr[i][j];
            }
        }

        moveArr(level, direction, newArr);
        // 이 함수에서 움직인다.

        if(!isChanged) return;

        // 위 아래 오른쪽 왼쪽 4방향으로 넘긴다.
        for(int i = 0; i < 4; i++){
            DFS(level + 1, i, newArr);
        }
    }

    private static void moveArr(int level, int direction, long[][] arr){
        // 모든 arr의 원소에 대해서 진행
        // 0이면 아래로 민다. -> 아래에서 위로 탐색
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                visited[i][j] = 0;
            }
        }
        isChanged = false;

        if(direction == 0){
            for(int i = N - 1; i >= 0; i--){
                for(int j = 0; j < N; j++){
                    if(arr[i][j] == 0) continue;
                    int nextY = i;
                    while(true){
                        if(nextY == N - 1){
                            long temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[nextY][j] = temp;
                            if(nextY != i) isChanged = true;
                            break;
                        }
                        else if(arr[nextY + 1][j] != 0){
                            if(arr[nextY + 1][j] == arr[i][j]
                                    && visited[nextY + 1][j] == 0){
                                arr[i][j] = 0;
                                arr[nextY + 1][j] <<= 1;
                                visited[nextY + 1][j] = 1;
                                isChanged = true;
                                break;
                            }
                            else {
                                long temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[nextY][j] = temp;
                                if(nextY != i) isChanged = true;
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
                            long temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[nextY][j] = temp;
                            if(nextY != i) isChanged = true;
                            break;
                        }
                        else if(arr[nextY - 1][j] != 0){
                            if(arr[nextY - 1][j] == arr[i][j]
                                    && visited[nextY - 1][j] == 0){
                                arr[i][j] = 0;
                                arr[nextY - 1][j] <<= 1;
                                visited[nextY - 1][j] = 1;
                                isChanged = true;
                                break;
                            }
                            else {
                                long temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[nextY][j] = temp;
                                if(nextY != i) isChanged = true;
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
                            long temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[i][nextX] = temp;
                            if(nextX != j) isChanged = true;
                            break;
                        }
                        else if(arr[i][nextX + 1] != 0){
                            if(arr[i][nextX + 1] == arr[i][j]
                                    && visited[i][nextX + 1] == 0){
                                arr[i][j] = 0;
                                arr[i][nextX + 1] <<= 1;
                                visited[i][nextX + 1] = 1;
                                isChanged = true;
                                break;
                            }
                            else {
                                long temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[i][nextX] = temp;
                                if(nextX != j) isChanged = true;
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
                            long temp = arr[i][j];
                            arr[i][j] = 0;
                            arr[i][nextX] = temp;
                            if(nextX != j) isChanged = true;
                            break;
                        }
                        else if(arr[i][nextX - 1] != 0){
                            if(arr[i][nextX - 1] == arr[i][j]
                                    && visited[i][nextX - 1] == 0){
                                arr[i][j] = 0;
                                arr[i][nextX - 1] <<= 1;
                                visited[i][nextX - 1] = 1;
                                isChanged = true;
                                break;
                            }
                            else {
                                long temp = arr[i][j];
                                arr[i][j] = 0;
                                arr[i][nextX] = temp;
                                if(nextX != j) isChanged = true;
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
