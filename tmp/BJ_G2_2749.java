/**

 @author 한규준
 @since 2023-08-12
 @see https://www.acmicpc.net/problem/2749
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_2749.java
 @youtube
 @performance 11624KB, 84ms
 @category 피보나치, 분할정복, 재귀, 수학, 행렬
 @note

 1 1
 1 0
 이 수열을 제곱하면 0 0은 Fi3, 0 1, 1 0은 Fi2, 1 1은 Fi1이 된다.
 따라서 제곱수로 나누어 계산하면 LogN으로 줄일 수 있다.

 */

package algo;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_G2_2749 {
    static long[][] origin = {{1, 1}, {1, 0}};
    static long n;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Long.parseLong(br.readLine());

        long[][] result = fi(new long[][]{{1, 1}, {1, 0}}, n - 1);

        bw.write(result[0][0] + "\n");
        bw.flush();
        bw.close();
    }

    public static long[][] fi(long[][] a, long exp){
        if(exp == 1 || exp == 0){
            return a;
        }

        long[][] tempArr = fi(a, exp / 2);

        tempArr = multifly(tempArr, tempArr);

        if(exp % 2 == 1L){
            tempArr = multifly(tempArr, origin);
        }

        return tempArr;
    }

    public static long[][] multifly(long[][] a, long[][] b){
        long[][] ret = new long[2][2];

        ret[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % 1000000;
        ret[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % 1000000;
        ret[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % 1000000;
        ret[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % 1000000;

        return ret;

    }
}
