/**

 @author 한규준
 @since 2023-08-10
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_17406.java/
 @youtube
 @performance 84420KB, 504ms
 @category 수열, 구현
 @note

1. 돌리는 함수를 만들고
 2. 돌리는 방법을 저장하는 배열을 만들어
 3. 해당 배열의 수열을 만들고
 4. 수열마다 배열을 돌린 뒤
 5. 행의 합 최소치를 구합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_17406 {
	static int N;
	static int M;
	static int K;
    static int minValue = Integer.MAX_VALUE;
    static int[][] arr;
    static rotation[] rot;

    // 회전방법 배열을 만들기 위한 클래스
    public static class rotation{
        public int r;
        public int c;
        public int s;
        public rotation(int r, int c, int s){
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        rot = new rotation[K];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            rot[i] = new rotation(r, c, s);
        }
        
        // 배열 및 회전방법배열 초기화
        // 수열
        permu(0, new rotation[K], new boolean[K]);

        System.out.println(minValue);
    }

    // 수열만들기(순서가 상관있으므로, 조합은 안됩니다.)
    // 1번째 방법 -> 2번째 방법을 이용해 돌리는 것과
    // 2번째 방법 -> 1번째 방법을 이용해 돌리는 것의 결과가 다릅니다.
    private static void permu(int nth, rotation[] tempArr, boolean[] visited){
        if(nth == tempArr.length){
            int[][] arrC = new int[N][M];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    arrC[i][j] = arr[i][j];
                }
            }
            rotate(arrC, tempArr);
            return;
        }

        for(int i = 0; i < tempArr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                tempArr[nth] = rot[i];
                permu(nth + 1, tempArr, visited);
                visited[i] = false;
            }
        }
    }

    // 만들어진 순열을 이용해 배열을 회전시킵니다.
    private static void rotate(int[][] arr, rotation[] rot){
        int r, c, s;
        for(int i = 0; i < rot.length; i++) {
            r = rot[i].r;
            c = rot[i].c;
            s = rot[i].s;

            // 둘레 : S * 8
            // 출발좌표 : (r-s, c-s)
            // 전진횟수 : S * 2 + 1
            // 이 3가지 사항에 대해, 1 <= s <= 최대
            int[] movX = {1, 0, -1, 0};
            int[] movY = {0, 1, 0, -1};
            for (int S = 1; S <= s; S++) {
                Deque<Integer> deque = new ArrayDeque<>();
                int round = S * 8;
                int movLimit = S * 2 + 1;

                int y = r - S;
                int x = c - S;
                int movCount = 1;
                int endMov = 1;
                int movV = 0;

                // 정해진 정사각형들의 둘레를 회전하며 큐에 넣습니다.
                while (endMov <= round) {
                    if (movCount == movLimit) {
                        movV++;
                        movCount = 1;
                    }

                    deque.add(arr[y][x]);
                    y += movY[movV];
                    x += movX[movV];
                    movCount++;
                    endMov++;
                }

                // 시계방향으로 회전시키기 위해 큐에서 빼고 다시 넣습니다.
                deque.addFirst(deque.pollLast());
                y = r - S;
                x = c - S;
                movCount = 1;
                endMov = 1;
                movV = 0;

                // 위에서 이용했던 방법으로, 사각형을 다시 입력합니다.
                while (endMov <= round) {
                    if (movCount == movLimit) {
                        movV++;
                        movCount = 1;
                    }

                    arr[y][x] = deque.poll();
                    y += movY[movV];
                    x += movX[movV];
                    movCount++;
                    endMov++;
                }
            }
        }

        // 회전된 배열의 행을 탐색하면서 최소값을 갱신합니다.
        for(int i = 0; i < N; i++){
            int tempSum = 0;
            for(int j = 0; j < M; j++){
                tempSum += arr[i][j];
            }
            if(tempSum < minValue) minValue = tempSum;
        }
    }
}
