/**

 @author 한규준
 @since 2023-08-21
 @see https://www.acmicpc.net/problem/11025
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_11025.java
 @youtube
 @performance 12236KB, 140ms
 @category 요세푸스, 다이나믹 프로그래밍
 @note

 점화식 세울때
 왜 모듈러연산을 n으로 하지 않고 i로 하는지 다시 보기

 */
package algo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_P5_11025 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int ans = 1;

        for(int i = 2; i <= N; i++){
            ans = (ans + K - 1) % i + 1;
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();

    }
}
