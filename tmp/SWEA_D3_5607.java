/**

 @author 한규준
 @since 2023-10-11
 @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AWXGKdbqczEDFAUo&solveclubId=AYlH3z4K78kDFAVR&problemBoxTitle=1010%EC%A3%BC&problemBoxCnt=5&probBoxId=AYsWdiua8IADFAV6
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D3_5607.java
 @youtube
 @performance 22288KB, 189ms
 @category 나머지정리, 분할정복, 페르마 소정리
 @note

 처음에는 DP와 나머지정리를 이용하거나
 나머지정리만 이용해도 될 줄 알았으나 오버플로우가 심하게 발생
 이후 페르마 소정리 글을 보고 분할정복까지는 구현했으나
 N! / R!(N - R)! 를 구하는 과정에서 식이 약간 잘못되어 수정했습니다.

 1. 이 문제의 경우 빠르게 양 끝단 케이스를 확인해야 합니다.
 2. 오버플로우 등의 문제가 확인되었다면, 나머지정리 및 나머지 제곱 규칙을 확인해야 합니다.
 3. 해당 방법을 구현합니다.

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_D3_5607 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            long a = 1, b = 1;
            for(int i = 2; i <= R; i++) b = b * i % 1234567891;
            for(int i = N; i > (N - R); i--) a = a * i % 1234567891;
            bw.write("#" + t + " " + (a % 1234567891) * dup(b, 1234567891 - 2) % 1234567891 + "\n");
        }

        bw.flush();
        bw.close();
    }

    // R 만큼 제곱
    private static long dup(long num, int R){
        if(R == 0) return 1;

//        2^(10000) = 2^(5000) * 2^(5000)
        long tmp;
        long result;
        if(R % 2 == 0){
            tmp = dup(num, R / 2) % 1234567891;
            result = (tmp * tmp) % 1234567891;
        } else {
            tmp = dup(num, R / 2) % 1234567891;
            result = ((tmp * tmp) % 1234567891) * num % 1234567891;
        }

        return result;
    }
}
