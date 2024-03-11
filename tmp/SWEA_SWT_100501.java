/**

 @author 한규준
 @since 2023-10-05
 @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWXRQm6qfL0DFAUo&probBoxId=AYr3k03KgA4DFAV6&type=PROBLEM&problemBoxTitle=1004%EC%A3%BC&problemBoxCnt=4
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_SWT_100501.java
 @youtube
 @performance 62656KB, 255ms
 @category 구현
 @note

벽돌을 깨는 프로그램을 구현하는 문제입니다.
 완탐 DFS를 돌려도 문제가 없을 것으로 생각합니다.
 최악의 경우
 N = 4, W = 12, H = 15이고
 DFS하나의 경우 W * H, 구슬을 쏘는 경우 W * H * N이므로
 12 * 12 * 15 * 15 * 4 = 129600회
 여기에 좌표탐색시간 12 * 15를 더 얹으면 2300만회 < 3초

 문제를 부분별로 나눕니다.
 1. 현재 맵에 있는 벽돌들의 가장 윗부분을 선택해서 DFS를 출발시킵니다.
 2. DFS에서는 구슬을 쏘는 횟수들이 되면 탐색을 종료하고 값을 갱신합니다.
 3. DFS내에서 맵을 복사하게 되고, 각각의 경우마다 폭발하는 모든 벽돌들을 Deque에 넣고 Deque가 빌때까지 계속 돌립니다.
 이때, 벽돌들을 바로바로 초기화해주지 않으면 문제가 생길 수 있습니다.
 4. 벽돌들이 모두 폭발했다면, 아래에서부터 시작해 벽돌들을 밑으로 정렬합니다.
 5. 정렬된 맵에서 가장 윗부분을 선택해서 DFS 다음 depth로 진행합니다.
 -> 목표 depth가 될 때까지 2~5를 반복합니다.

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_SWT_100501 {
    private static int count = 0;
    private static int result;
    private static int N, H, W;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for(int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] map = new int[H][W];

            result = 0;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0)
                        result++;
                }
            }

            for (int i = 0; i < W; i++) {
                for (int j = 0; j < H; j++) {
                    if (map[j][i] != 0) {
                        DFS(j, i, 1, map);
                        break;
                    }
                }
            }

            bw.write("#" + t + " " + result + "\n");
        }
        bw.flush();
        bw.close();
    }

    // y, x에 공을 쐈을 때
    private static void DFS(int y, int x, int depth, int[][] map) {
        count++;
        if (depth == N + 1) {
            int tmp = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] != 0) {
                        tmp++;
                    }
                }
            }
            result = Math.min(result, tmp);
            return;
        }

        int[][] tmpMap = new int[H][W];

        for (int i = 0; i < H; i++) {
            tmpMap[i] = map[i].clone();
        }

        Deque<int[]> deq = new ArrayDeque<>();
        deq.offer(new int[] { y, x, tmpMap[y][x] });
        tmpMap[y][x] = 0;

        while (!deq.isEmpty()) {
            int[] nowYX = deq.poll();

            for (int i = 0; i < W; i++) {
                if (nowYX[1] - nowYX[2] < i && i < nowYX[1] + nowYX[2] && tmpMap[nowYX[0]][i] > 1) {
                    deq.offer(new int[] { nowYX[0], i, tmpMap[nowYX[0]][i] });
                    tmpMap[nowYX[0]][i] = 0;
                }
                else if(nowYX[1] - nowYX[2] < i && i < nowYX[1] + nowYX[2] && tmpMap[nowYX[0]][i] == 1){
                    tmpMap[nowYX[0]][i] = 0;
                }
            }
            for (int i = 0; i < H; i++) {
                if (nowYX[0] - nowYX[2] < i && i < nowYX[0] + nowYX[2] && tmpMap[i][nowYX[1]] > 1) {
                    deq.offer(new int[] { i, nowYX[1], tmpMap[i][nowYX[1]] });
                    tmpMap[i][nowYX[1]] = 0;
                }
                else if(nowYX[0] - nowYX[2] < i && i < nowYX[0] + nowYX[2] && tmpMap[i][nowYX[1]] == 1){
                    tmpMap[i][nowYX[1]] = 0;
                }
            }
        }

        // 정리 - 밑에서부터 당겨야 한다
        for (int i = 0; i < W; i++) {
            for (int j = H - 1; j >= 0; j--) {
                if (tmpMap[j][i] != 0) {
                    int tmp = tmpMap[j][i];
                    tmpMap[j][i] = 0;
                    for (int k = j; k < H; k++) {
                        if (k == H - 1 && tmpMap[H - 1][i] == 0) {
                            tmpMap[H - 1][i] = tmp;
                            break;
                        } else if (tmpMap[k][i] != 0) {
                            tmpMap[k - 1][i] = tmp;
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (tmpMap[j][i] != 0) {
                    DFS(j, i, depth + 1, tmpMap);
                    break;
                } else if(j == H - 1 && tmpMap[j][i] == 0) {
                    int tmpcnt = 0;
                    for(int k = 0; k < H; k++){
                        for(int m = 0; m < W; m++){
                            if(tmpMap[k][m] != 0) tmpcnt++;
                        }
                    }
                    result = Math.min(result, tmpcnt);
                }
            }
        }
    }
}
