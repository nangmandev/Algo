/**

 @author 한규준
 @since 2023-08-11
 @see https://www.acmicpc.net/problem/10830
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_10830_MATH.java
 @youtube
 @performance 11540KB, 80ms
 @category
 @note

 1629번과 비슷한 맥락
 지수를 나눠서 재귀하기

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_10830_MATH {
    static int N;
    static long B;
    static int[][] origin;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        origin = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                origin[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = pow(origin, B);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static int[][] pow(int[][] A, long exp){
        if(exp == 1L){
            return A;
        }
        int[][] tempArr = pow(A, exp / 2);

        tempArr = arrayMultiply(tempArr, tempArr);

        if(exp % 2 == 1L){
            tempArr = arrayMultiply(tempArr, A);
        }

        return tempArr;
    }

    public static int[][] arrayMultiply(int[][] o1, int[][] o2){
        int[][] temp = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    temp[i][j] += o1[i][k] * o2[k][j];
                    temp[i][j] %= 1000;
                }
            }
        }
        return temp;
    }
}
