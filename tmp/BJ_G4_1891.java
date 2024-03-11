/**

 @author 한규준
 @since 2023-08-19
 @see https://www.acmicpc.net/problem/1891
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1891.java
 @youtube
 @performance 11508Kb, 80ms
 @category 분할정복
 @note

 정석적인 사분면 분할하면서 좌표 따라가기 문제

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_1891 {
    // 최대값이 2의 50제곱이므로 long
    static long N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());
        String num = st.nextToken();
        N = (long)Math.pow(2, d);
        long originY = 0, originX = 0;

        // 초기화 끝

        int idx = 0;
        for(long i = N / 2; i >= 1; i /= 2){
            // 1사분면이면 x좌표 n / 2만큼 더하기
            // 2사분면이면 패스
            // 3사분면이면 y좌표 n / 2만큼 더하기
            // 4사분면이면 x, y 둘다 더하기
            if(num.charAt(idx) - '0' == 1){
                originX += i;
            }
            else if(num.charAt(idx) - '0' == 3){
                originY += i;
            }
            else if(num.charAt(idx) - '0' == 4){
                originX += i;
                originY += i;
            }
            idx++;
        }

        // 처음 받은곳 좌표 알아내기 끝
        // 찾아야 할 곳 좌표 알아내기
        st = new StringTokenizer(br.readLine());
        long newX = originX + Long.parseLong(st.nextToken());
        long newY = originY - Long.parseLong(st.nextToken());

        // 새 좌표가 유효하면 진행
        if(newX >= 0 && newX < N
            && newY >= 0 && newY < N) {
            char[] result = new char[d];
            idx = 0;

            // 1, 2, 3, 4사분면 따라가기
            for (long i = N / 2; i >= 1; i /= 2) {
                if (newY >= i && newX >= i) {
                    result[idx++] = '4';
                    newY -= i;
                    newX -= i;
                } else if (newY >= i) {
                    result[idx++] = '3';
                    newY -= i;
                } else if (newX >= i) {
                    result[idx++] = '1';
                    newX -= i;
                } else {
                    result[idx++] = '2';
                }
            }

            for (int i = 0; i < d; i++) {
                sb.append(result[i]);
            }
        } else {
            sb.append(-1);
        }

        System.out.println(sb);
    }
}
