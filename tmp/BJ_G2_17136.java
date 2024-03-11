/**

 @author 한규준
 @since 2023-08-27
 @see https://www.acmicpc.net/problem/17136
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_17136.java
 @youtube
 @performance 20524KB, 224ms
 @category 백트래킹, 구현
 @note

1. 탐색을 진행하며 1을 발견할 때마다 색종이 1~5를 붙일 수 있는지 확인하고, 가능하면 붙인다.
 2. 다음 탐색은 배열의 현재 주소부터 시작한다. 이때, 현재까지 발견한 1범위에 색종이를 붙이지 못했다면 탐색을 중지한다.
 3. 이 범위는 1을 발견할 때마다 count를 매개변수로 더하여 넘기고, 25에서 rest의 총 값을 뺀 것이 같음으로 알 수 있다.
 4. 배열의 마지막 주소까지 검사가 끝나면 색종이 개수를 비교한다.
 5. 이 과정이 마무리되면 최종 개수를 출력한다.

 */
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G2_17136 {
    static int[][] arr;
    static int[] rest;
    static int minResult = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[10][10];
        rest = new int[6];

        for(int i = 1; i <= 5; i++) rest[i] = 5;

        int flag = 0;
        for(int i = 0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) flag = 1;
            }
        }

        if(flag == 1) DFS(0, 0, 0);

        if(flag == 0) System.out.println(0);
        else if(minResult == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minResult);

    }

    private static void DFS(int y, int x, int count){
        for(int i = y; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(arr[i][j] == 1){
                    count++;
                    for(int k = 1; k <= 5; k++){
                        if(canAttach(i, j, k) && rest[k] > 0){
                            attach(i, j, k);
                            rest[k]--;
                            // DFS로 들어갔다 바로탈출
                            DFS(i, j, count);
                            detach(i, j, k);
                            rest[k]++;
                        }
                    }
                    int rst = 0;
                    for(int k = 1; k <= 5; k++){
                        rst += rest[k];
                    }
                    if(rst != 25 - count) return;
                }

                if(i == 9 && j == 9){
                    for(int l = 0; l < 10; l++){
                        for(int k = 0; k < 10; k++){
                            if(arr[i][j] == 1) return;
                        }
                    }
                    int temp = 0;
                    for(int l = 1; l <= 5; l++){
                        temp += 5 - rest[l];
                    }
                    minResult = Math.min(minResult, temp);
                    return;
                }
            }
        }
    }

    // x, y 좌표에 한 변이 n인 색종이가 붙을 수 있는지 확인
    private static boolean canAttach(int y, int x, int n){
        if(y + n > 10 || x + n > 10) return false;
        for(int i = y; i < y + n; i++){
            for(int j = x; j < x + n; j++){
                if(arr[i][j] == 0) return false;
            }
        }
        return true;
    }

    // 색종이 붙이기
    private static void attach(int y, int x, int n){
        for(int i = y; i < y + n; i++){
            for(int j = x; j < x + n; j++){
                arr[i][j] = 0;
            }
        }
    }

    // 색종이 떼기
    private static void detach(int y, int x, int n){
        for(int i = y; i < y + n; i++){
            for(int j = x; j < x + n; j++){
                arr[i][j] = 1;
            }
        }
    }
}
