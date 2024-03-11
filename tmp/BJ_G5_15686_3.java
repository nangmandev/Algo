/**

 @author 한규준
 @since 2023-08-04
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_15686_3.java
 @youtube
 @performance
 @category BFS
 @note

BFS탐색
 -> 실패

 */
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_G5_15686_3 {
    static int N;
    static int M;
    static int[][] arr;
    static int count;
    static int removeCount;
    static int[][] visited;

    static Deque<int[]> deq = new ArrayDeque<>();
    static int thisBfsCount = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int nextX = 0;
    static int nextY = 0;
    static int[] nowYX;

    static int nowPointCount;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N x N 행렬
        // r행 c열
        // 치킨거리 : 가장 가까운 치킨집과의 거리
        // 도시의 치킨거리 : 모든 집의 치킨거리의 합
        // 가장 많은 수익이 가능한 치킨집의 개수 : M개
        // 도시의 치킨거리가 가장 작게 하는 치킨집의 개수

        // 치킨집을 하나씩 지워가면서 확인

        // M이 미리 주어짐
        // 폐업해야 할 치킨집의 개수 : 현재 2개수 - M

        // 처음 치킨거리 구하기 -> 모든 1에 대해 BFS로 탐색하면서 2만나면 거리저장, 끝
        // 배열 1씩 돌면서 2만나면 -> 0으로 바꾸고, BFS탐색하고, 짧으면 거리저장하고 다음 치킨집도 없애보고

        String[] temp = br.readLine().split(" ");
        N = Integer.parseInt(temp[0]);
        M = Integer.parseInt(temp[1]);
        arr = new int[N][N];
        count = Integer.MAX_VALUE;
        int tempCount = 0;

        for(int i = 0; i < N; i++) {
            temp = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(temp[j]);
                if(arr[i][j] == 2) tempCount++;
            }
        }

        removeCount = tempCount - M;

        // 초기화 끝
        // 첫 치킨거리 구하기 -> 필요없음. M개만 구하면됨

        countMaxChicken(arr, 0, 0, 0);

        System.out.println(count);
    }

    private static void countMaxChicken(int[][] tempArr, int removeNow, int y, int x) {
        // 여기서는 반복문을 돌면서 치킨집이 몇개인지 확인하고 BFS준다.
        // 제거한 치킨집 개수가 일치하면 BFS돌리고 리턴
        if(removeNow == removeCount) {
            // BFS에서는 전체탐색을 하고 카운트를 갱신
            BFS(tempArr);
            return;
        }

        // 백트래킹을 이용, 
        for(int i = y; i < N; i++) {
            // 어떠한 줄의 중간에서 시작하는 경우
            if(i == y) {
                for(int j = x; j < N; j++) {
                    if(tempArr[i][j] == 2) {
                        // 치킨집 삭제하고, 삭제된 배열 넘기고, removenow + 1해서 넘기고
                        // 해당 분기가 끝나고 돌아오면 배열 원상복구
                        tempArr[i][j] = 0;
                        countMaxChicken(tempArr, removeNow + 1, i, j);
                        tempArr[i][j] = 2;
                    }
                }
                // 그렇지 않은 경우
            } else {
                for(int j = 0; j < N; j++) {
                    if(tempArr[i][j] == 2) {
                        // 치킨집 삭제하고, 삭제된 배열 넘기고, removenow + 1해서 넘기고
                        // 해당 분기가 끝나고 돌아오면 배열 원상복구
                        tempArr[i][j] = 0;
                        countMaxChicken(tempArr, removeNow + 1, i, j);
                        tempArr[i][j] = 2;
                    }
                }
            }
        }
    }

    private static void BFS(int[][] tempArr) {
        deq = new ArrayDeque<>();
        thisBfsCount = 0;
        dx = new int[]{1, -1, 0, 0};
        dy = new int[]{0, 0, 1, -1};
        nextX = 0;
        nextY = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(tempArr[i][j] == 1) {
                    deq.add(new int[] {i, j});
                    visited = new int[N][N];
                    nowPointCount = Integer.MAX_VALUE;
                    // 여기에서 바로바로 시작해야한다.

                    while(deq.size() != 0) {
                        nowYX = deq.pollFirst();
                        if(tempArr[nowYX[0]][nowYX[1]] == 2) {
                            if(nowPointCount > visited[nowYX[0]][nowYX[1]]) {
                                nowPointCount = visited[nowYX[0]][nowYX[1]];
                            }

                        }
                        for(int k = 0; k < 4; k++) {
                            nextY = nowYX[0] + dy[k];
                            nextX = nowYX[1] + dx[k];
                            if(nextX >= 0 && nextX < N
                                    && nextY >= 0 && nextY < N
                                    && visited[nextY][nextX] == 0) {
                                //&& arr[nextY][nextX] != 1) {
                                deq.add(new int[]{nextY, nextX});
                                visited[nextY][nextX] = visited[nowYX[0]][nowYX[1]] + 1;

                            }
                        }
                    }
                    thisBfsCount += nowPointCount;
                    //System.out.println(nowPointCount);
                    deq.clear();
                }
            }
        }
        // 현재 BFS 끝
        if(thisBfsCount < count) {
            count = thisBfsCount;
        }
    }
}
