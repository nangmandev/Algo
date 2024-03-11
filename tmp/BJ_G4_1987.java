/**

 @author 한규준
 @since 2023-08-18
 @see https://www.acmicpc.net/problem/1987
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1987.java
 @youtube
 @performance 12352KB, 948ms
 @category DFS
 @note

1. BFS로는 힘들듯합니다(최장거리를 구하므로)
 2. DFS를 구현합니다.
 3. 배열을 사용하면 탐색시 시간초과가 날 가능성이 있어 보입니다.
-> map을 사용하여 처리하거나, A~Z까지의 알파벳이므로 문자 - 'A'를 적용한 visited배열을 만듭니다.
 -> map사용결과 : 295644KB, 1844ms
 -> 배열사용결과 : 12352KB, 948ms
 배열을 사용한 결과 공간/시간복잡도가 매우 단축된 것을 볼 수 있습니다.
 4. 경로를 모두 이동한 후에는 visited배열을 다시 0으로 만들어, 다음 경로 통과를 허가합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G4_1987 {
    static int R, C;
    static char arr[][];
    static int[] movY = {1, -1, 0, 0};
    static int[] movX = {0, 0, 1, -1};
    static int result = 0;
    static int[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];

        for(int i = 0; i < R; i++){
            String temp = br.readLine();
            for(int j = 0; j < C; j++){
                arr[i][j] = temp.charAt(j);
            }
        }

        visited = new int[26];

        // 초기화 완료
        // 행, 열 크기 변수, 탐색에 사용할 맵 2차원 배열, 중복체크에 사용할 배열을 선언합니다.
        // 탐색시 알파벳에 해당하는 배열에 visited표시, 빠져나올때 해제
        DFS(0, 0, 1);

        System.out.println(result);
    }

    public static void DFS(int y, int x, int count){
        // 현재 칸의 알파벳에 해당하는 visited배열을 확인합니다.
        // 이미 통과한 적이 있는 알파벳이면 종료합니다.
        if(visited[arr[y][x] - 'A'] == 1){
            return;
        }
        // 현재 칸에 있는 알파벳에 들리지 않았다면 체크합니다.
        // 그 뒤, 최대 탐색 카운트와 비교합니다.
        visited[arr[y][x] - 'A'] = 1;
        result = Math.max(count, result);

        // 4방향으로 비교합니다.(범위 확인)
        for(int i = 0; i < 4; i++){
            int nextY = y + movY[i];
            int nextX = x + movX[i];
            if(nextY >= 0 && nextY < R
            && nextX >= 0 && nextX < C){
                // 다음 경로로 진행합니다.
                DFS(nextY, nextX, count + 1);
            }
        }
        // 모든 경로를 탐색하고 다시 돌아올 때 visited를 해제합니다.
        visited[arr[y][x] - 'A'] = 0;
    }
}
