/**

 @author 한규준
 @since 2023-08-08
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_16926.java
 @youtube
 @performance 38796KB, 456ms
 @category 구현, 큐
 @note

 1. 사각형을 외부부터 내부까지 순서대로(사각형으로 돌면서) 탐색
 2. 둘레는 윗변 * 2 + 아랫변 * 2 - 4
 3. R이 10억번으로 매우 많으므로 사각형 전체를 돌릴 수 없음.
 4. 따라서, 외부부터 내부까지 분할하여 돌려야 함
 5. 한바퀴 돌때 12회 회전해야 한다면, R을 12로 모듈러 연산을 한 결과가 일치하고 연산횟수도 훨씬 적음
 6. 사각형의 좌표는 N, M에서 중앙으로 들어감 -> N, M에서 1씩 늘리면서 빼서 재귀처리.
 7. 마지막 값은 N / 2보다 커야함(그렇지 않으면 범위를 벗어남)
 8. R을 12로 모듈러 연산을 한 결과와 둘레를 구한 결과로 갯수, 회전수를 알아냄
 9. 1번에서 만든 코드를 이용해 사각형 둘레를 큐에 넣음
 10. 큐에 넣은 자료들을 회전수만큼 앞에서 빼서 뒤로 입력
 11. 해당 과정을 마친 뒤 다시 순서대로 입력하면 됨.
 12. 맨 안쪽의 사각형까지 반복하면 끝

 골드5 16927번 문제와 같지만, 16926번 문제는 R이 적어 그냥 돌려도 통과가 가능하다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class BJ_S1_16926 {
    private static int N;
    private static int M;
    private static int R;
    private static int[][] arr;

    private static int[] movX = {1, 0, -1, 0};
    private static int[] movY = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rot(0);

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void rot(int nowMinus) {
        if(nowMinus >= N / 2 || nowMinus >= M / 2) return;

        // 현재둘레
        int nowRound = (N - (nowMinus * 2)) * 2 + (M - (nowMinus * 2)) * 2 - 4;
        // 모듈러연산한 횟수만 돌면된다.
        int nowRotation = R % nowRound;
        // 돌고
        rotation(nowMinus, nowRound, nowRotation);
        // 다음
        rot(nowMinus + 1);
    }

    private static void rotation(int nowMinus, int nowRound, int nowRotation) {
        Deque<Integer> deq = new ArrayDeque<Integer>();
        // 0이면 X, 1이면 Y, 2면 -X, 3면 -Y
        int movXY = 0;
        int count = 1;
        int nowX = 1 + nowMinus;
        int nowY = 1 + nowMinus;

        for(int i = 0; i < nowRound; i++) {
            if(movXY % 2 == 0 && count == M - (nowMinus * 2)) {
                count = 1;
                movXY++;
            }
            if(movXY % 2 == 1 && count == N - (nowMinus * 2)) {
                count = 1;
                movXY++;
            }
            deq.add(arr[nowY][nowX]);
            count++;
            nowX += movX[movXY];
            nowY += movY[movXY];
        }

        for(int i = 0; i < nowRotation; i++) {
            deq.add(deq.poll());
        }

        movXY = 0;
        count = 1;
        nowX = 1 + nowMinus;
        nowY = 1 + nowMinus;

        for(int i = 0; i < nowRound; i++) {
            if(movXY % 2 == 0 && count == M - (nowMinus * 2)) {
                count = 1;
                movXY++;
            }
            if(movXY % 2 == 1 && count == N - (nowMinus * 2)) {
                count = 1;
                movXY++;
            }
            arr[nowY][nowX] = deq.poll();
            count++;
            nowX += movX[movXY];
            nowY += movY[movXY];
        }
    }
}
