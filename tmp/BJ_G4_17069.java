/**

 @author 한규준
 @since 2023-10-12
 @see https://www.acmicpc.net/problem/17069
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_17069.java
 @youtube
 @performance 12008KB, 84ms
 @category DP
 @note

 파이프 옮기기 1은 DFS로 탐색이 가능했습니다.
 하지만, 이 경우 테스트케이스에 친절하게 43억회가 답이라 적혀있기 때문에
 0.5초 내로 43억회를 탐색하는 것은 불가능합니다.
 따라서 DP를 이용하여 문제를 풀었습니다.

 다만, 파이프의 각도를 최대 45도만큼 변경할 수 있습니다.
 그러므로 다음 좌표로 갈 수 있는 경우의 수를 구하기 위해 현재 좌표의 파이프 상태를 저장할 필요가 있습니다.
 처음에는 객체를 이용해보려 했으나, 마찬가지로 43억회 탐색해야 할 것으로 예상되어...
 visited배열을 3개 만들어 각각 가로, 대각선, 세로를 저장하고
 각각의 경우에서 갈 수 있는 경우의 수를 구해 파이프 상태에 맞는 visited에 저장하기로 했습니다.

 1. 첫 가로 상태 visited1을 1로 저장합니다(초기값이 0이 되면 구할 수 없습니다...)
 2. 현재 가능한 상태 중 가로인 경우의 수(visited1)를 다음 가로(visited1), 대각선(visited2)에 추가합니다.
 3. 현재 가능한 상태 중 대각선인 경우의 수(visited2)를 가로(visited1), 대각선(visited2), 세로(visited3)에 추가합니다.
 4. 현재 가능한 상태 중 세로인 경우의 수(visited3)를 대각선(visited2), 세로(visited3)에 추가합니다.
 5. 2~4를 배열을 돌며 반복합니다. 파이프는 우측, 우측아래 대각선, 아래로만 움직일 수 있으므로, 왼쪽 -> 오른쪽, 위 -> 아래로 탐색합니다.(거꾸로 X)
 6. 이때, 대각선의 경우 가로, 세로, 대각선이 모두 비어있어야 하므로 해당 경우를 주의합니다.
 7. 마지막으로 지도의 끝에 파이프가 각각 가로, 세로, 대각선의 상태로 들어온 경우를 모두 더합니다(visited1, 2, 3의 끝)

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_17069 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N - 1];
        long[][][] visited = new long[N][N - 1][3];

        visited[0][0][0] = 1;

        // 입력
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(j == 0) continue;
                map[i][j - 1] = tmp;
            }
        }

        // 0 : 가로 1 : 대각선 2 : 세로
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N - 1; j++){
                // 가로로 가려면, 가로이거나 대각선이어야 한다
                if(j + 1 < N - 1 && map[i][j + 1] == 0){
                    visited[i][j + 1][0] += visited[i][j][0];
                    visited[i][j + 1][0] += visited[i][j][1];
                }
                // 대각선으로 가려면 전부 상관없다
                if(j + 1 < N - 1 && i + 1 < N
                        && map[i + 1][j + 1] == 0
                        && map[i + 1][j] == 0
                        && map[i][j + 1] == 0){
                    visited[i + 1][j + 1][1] += visited[i][j][0];
                    visited[i + 1][j + 1][1] += visited[i][j][1];
                    visited[i + 1][j + 1][1] += visited[i][j][2];
                }
                // 세로로 가려면, 세로이거나 대각선이어야 한다
                if(i + 1 < N && map[i + 1][j] == 0){
                    visited[i + 1][j][2] += visited[i][j][1];
                    visited[i + 1][j][2] += visited[i][j][2];
                }
            }
        }
        long res = visited[N - 1][N - 2][0] + visited[N - 1][N - 2][1] + visited[N - 1][N - 2][2];
        System.out.println(res);
    }
}
