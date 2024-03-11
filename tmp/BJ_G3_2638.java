/**

 @author 한규준
 @since 2023-08-11
 @see https://www.acmicpc.net/problem/2638
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_2638.java
 @youtube
 @performance 62812KB, 260ms
 @category BFS
 @note

1. 2면이 외부에 노출된 치즈는 BFS로 2회 접근됩니다.
 2. arr자체를 실시간으로 바꾸는 경우, 다음 탐색에서 오류가 발생합니다. -> 안됨
 3. visited를 이용해, 그냥 공간인 경우 1, 치즈인 경우 2, 치즈를 재방문한 경우 3으로 처리합니다.
 4. 1은 방문 불가, 2는 3으로, 3은 처리하지 않습니다. -> 2회 이상(2면 이상)노출된 경우 한꺼번에 처리
 5. BFS를 마치면 visited배열이 완성되고, visited배열이 3인 곳의 치즈를 지웁니다.
 6. 치즈를 지울 때마다 removeCount를 늘립니다.
 7. 맨 처음 arr을 만들때 cheese들을 세어 cheeseCount를 만들었습니다.
 8. removeCount와 cheeseCount가 일치한다면 모든 치즈가 지워진 것입니다.
 9. 정답

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G3_2638 {
    static int N, M;
    static int[][] arr;
    static int cheeseCount;
    static int removeCount;
    static int timeCount;

    public static class Node{
        int y;
        int x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1) cheeseCount++;
             }
        }

        timeCount = 0;

        // 초기화 완료
        // 이미 visited된 곳에 한번 더 접근했고, 그곳이 치즈면 지우고 다시 BFS시작
        // 방문했는데 치즈면 visited를 2로 만들고 컷
        // 굳이 모든 배열을 검사하지 말고
        // 치즈 개수를 세고, 그만큼 지우면 끝나도록
        // 0, 0에서 시작한다.

        while(cheeseCount != removeCount){
            BFS(new int[N][M]);
            timeCount++;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                }
            }
        }

        bw.write(timeCount + "\n");
        bw.flush();
        bw.close();
    }

    private static void BFS(int[][] visited){
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(new Node(0, 0));
        visited[0][0] = 1;

        while(!deque.isEmpty()){
            Node nowNode = deque.poll();

            int[] movX = {1, 0, -1, 0};
            int[] movY = {0, 1, 0, -1};
            for(int i = 0; i < 4; i++){
                int nextY = nowNode.y + movY[i];
                int nextX = nowNode.x + movX[i];

                if(nextY >= 0 && nextY < N
                && nextX >= 0 && nextX < M){
                    if(visited[nextY][nextX] == 0 && arr[nextY][nextX] == 0){
                        visited[nextY][nextX] = 1;
                        deque.add(new Node(nextY, nextX));
                    }
                    else if(visited[nextY][nextX] == 0 && arr[nextY][nextX] == 1){
                        visited[nextY][nextX] = 2;
                    }
                    else if(visited[nextY][nextX] == 2){
                        visited[nextY][nextX] = 3;
                    }
                }
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] == 3){
                    arr[i][j] = 0;
                    removeCount++;
                }
            }
        }
    }
}
