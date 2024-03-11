/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S5_2563.java
 @youtube
 @performance 11704KB, 76ms
 @category 구현
 @note

1. 색종이 전체 합을 구하고 겹치는구간을 빼서 결과를 출력하는가
 2. 배열에 구간을 계속 색칠하고 10000번 탐색하는가
 2번을 선택하면 매우 쉽지만
 1번을 선택하면 매우 어려움
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_S5_2563 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 인덱스를 1부터 100까지 쓰기 위해서 101씩 만듭니다.
        int[][] arr = new int[101][101];

        int N = Integer.parseInt(br.readLine());
        int maxValue = 0;

        // 입력값을 받고, 해당 입력값 ~ + 9까지의 영역을 배열에 표시합니다.
        // 중복입력이 되더라도, 1로 같습니다.
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for(int k = b; k < b + 10; k++){
                for(int g = a; g < a + 10; g++){
                    arr[k][g] = 1;
                }
            }
        }

        // 1~100까지 탐색하면서 1로 색칠된 영역을 모두 더합니다.
        for(int i = 1; i <= 100; i++){
            for(int j = 1; j <= 100; j++){
                if(arr[i][j] == 1) maxValue++;
            }
        }

        bw.write(maxValue + "\n");
        bw.flush();
        bw.close();
    }
}
