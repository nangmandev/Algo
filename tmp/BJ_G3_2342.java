/**

 @author 한규준
 @since 2023-09-17
 @see https://www.acmicpc.net/problem/2342
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_2342.java
 @youtube
 @performance 31448KB, 272ms
 @category DP
 @note

왼발, 오른발 상태를 전부다 배열에 저장(객체 X)

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_2342 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int idx = 1;
        int[] arr = new int[100001];

        st = new StringTokenizer(br.readLine());
        while(true){
            int temp = Integer.parseInt(st.nextToken());
            if(temp == 0) break;
            arr[idx++] = temp;
        }

        if(idx == 1){
            System.out.println(0);
        } else {
            // 왼발, 오른발, 회차
            int[][][] check = new int[5][5][idx];

            int temp = arr[1];
            check[temp][0][1] = 2;
            check[0][temp][1] = 2;

            for (int i = 2; i < idx; i++) {
                temp = arr[i];

                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        // 유효한 값이면 계산
                        if (check[j][k][i - 1] != 0) {
                            // 왼발을 움직이는경우
                            if (check[temp][k][i] == 0) {
                                check[temp][k][i] = check[j][k][i - 1] + getVal(j, temp);
                            } else
                                check[temp][k][i] = Math.min(check[temp][k][i], check[j][k][i - 1] + getVal(j, temp));
                            // 오른발을 움직이는경우
                            if (check[j][temp][i] == 0) {
                                check[j][temp][i] = check[j][k][i - 1] + getVal(k, temp);
                            } else
                                check[j][temp][i] = Math.min(check[j][temp][i], check[j][k][i - 1] + getVal(k, temp));
                        }
                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (check[i][j][idx - 1] != 0) {
                        result = Math.min(result, check[i][j][idx - 1]);
                    }
                }
            }

            System.out.println(result);
        }
    }

    private static int getVal(int prev, int now){
        if(prev == now) return 1;
        if(prev == 0) return 2;
        if(Math.abs(prev - now) == 2) return 4;
        return 3;
    }
}
