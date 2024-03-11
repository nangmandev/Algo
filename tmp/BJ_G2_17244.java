/**

 @author 한규준
 @since 2023-09-28
 @see https://www.acmicpc.net/problem/17244
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_17244.java
 @youtube
 @performance 15828KB, 116ms
 @category BFS, 비트마스킹
 @note

 물건들을 순서 정해서 0~4까지의 숫자로 표현
 visited배열을 2^4까지 만들고
 쭉 검사하며 진행합니다.

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G2_17244 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        int[][][] visited = new int[N][M][64];

        int[] startYX = new int[3];

        char x = '0';
        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = temp.charAt(j);
                if(map[i][j] == 'S'){
                    startYX[0] = i;
                    startYX[1] = j;
                    map[i][j] = '.';
                    visited[i][j][0] = 1;
                } else if(map[i][j] == 'X'){
                    map[i][j] = x++;
                }
            }
        }

        int max = (1 << (x - '0')) - 1;
        Deque<int[]> deq = new ArrayDeque<>();
        deq.offer(startYX);

        int result = Integer.MAX_VALUE;

        int[] movY = new int[]{-1, 1, 0, 0};
        int[] movX = new int[]{0, 0, -1, 1};
        while(!deq.isEmpty()){
            int[] nowYX = deq.poll();
            if(result != Integer.MAX_VALUE) break;

            for(int i = 0; i < 4; i++){
                int nextY = nowYX[0] + movY[i];
                int nextX = nowYX[1] + movX[i];

                if(nextY >= 0 && nextY < N
                && nextX >= 0 && nextX < M
                && map[nextY][nextX] != '#'){

                    if(map[nextY][nextX] >= '0' && map[nextY][nextX] <= '9'){
                        int nextThing = 1 << (map[nextY][nextX] - '0');
                        if((nowYX[2] & nextThing) == 0){
                            visited[nextY][nextX][nextThing + nowYX[2]] = visited[nowYX[0]][nowYX[1]][nowYX[2]] + 1;
                            deq.offer(new int[]{nextY, nextX, nextThing + nowYX[2]});
                        }
                        else if(visited[nextY][nextX][nowYX[2]] == 0){
                            visited[nextY][nextX][nowYX[2]] = visited[nowYX[0]][nowYX[1]][nowYX[2]] + 1;
                            deq.offer(new int[]{nextY, nextX, nowYX[2]});
                        }
                    }
                    else if(map[nextY][nextX] == 'E' && nowYX[2] == max){
                        result = Math.min(result, visited[nowYX[0]][nowYX[1]][nowYX[2]]);
                        break;
                    } else {
                        if(visited[nextY][nextX][nowYX[2]] == 0){
                            visited[nextY][nextX][nowYX[2]] = visited[nowYX[0]][nowYX[1]][nowYX[2]] + 1;
                            deq.offer(new int[]{nextY, nextX, nowYX[2]});
                        }
                    }
                }
            }
        }

        System.out.println(result);
    }
}
