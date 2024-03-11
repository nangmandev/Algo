/**

 @author 한규준
 @since 2023-08-17
 @see https://www.acmicpc.net/problem/1780
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_1780.java
 @youtube
 @performance 323740KB, 1272ms
 @category 재귀, 분할정복, 문제읽기
 @note

 사각형을 9개로 나누면서 카운트합니다.
 문제를 잘 읽어야 합니다.
 -> -1, 0, 1을 따로 저장해야 합니다.


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_1780 {
    static int N;
    static int[][] arr;
    static int countMO = 0;
    static int countZero = 0;
    static int countOne = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divied(0, 0, N);

        System.out.println(countMO + "\n" + countZero + "\n" + countOne);
    }

    public static void divied(int y, int x, int n){
        boolean findMO = false;
        boolean findOne = false;
        boolean fineZero = false;

        for(int i = y; i < y + n; i++){
            for(int j = x; j < x + n; j++){
                if(arr[i][j] == -1) findMO = true;
                else if(arr[i][j] == 0) fineZero = true;
                else if(arr[i][j] == 1) findOne = true;
            }
        }

        if((fineZero && !findOne && !findMO)){
            countZero++;
        }
        else if(!fineZero && findOne && !findMO){
            countOne++;
        }
        else if(!fineZero && !findOne && findMO){
            countMO++;
        }
        else {
            divied(y, x, n / 3);
            divied(y, x + n / 3, n / 3);
            divied(y, x + n / 3 * 2, n / 3);
            divied(y + n / 3, x, n / 3);
            divied(y + n / 3, x + n / 3, n / 3);
            divied(y + n / 3, x + n / 3 * 2, n / 3);
            divied(y + n / 3 * 2, x, n / 3);
            divied(y + n / 3 * 2, x + n / 3, n / 3);
            divied(y + n / 3 * 2, x + n / 3 * 2, n / 3);
        }
    }
}
