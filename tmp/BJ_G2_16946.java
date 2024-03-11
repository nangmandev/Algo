/**

 @author 한규준
 @since 2023-09-17
 @see https://www.acmicpc.net/problem/16946
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_16946.java
 @youtube
 @performance
 @category BFS, 공간집합
 @note

 문제를 좀 잘 읽자(%10)

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BJ_G2_16946 {
    static int N, M;
    static int[][] map;
    static int count = 2;
    static HashMap<Integer, Integer> hash;
    static int[] movY = {-1, 1, 0, 0};
    static int[] movX = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        hash = new HashMap<>();
        map = new int[N][M];
        int[][] origin = new int[N][M];

        for(int i = 0; i < N; i++){
            String temp = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = temp.charAt(j) - '0';
                origin[i][j] = temp.charAt(j) - '0';
            }
        }

        // 초기화 완료

        // 돌면서 0인곳마다 BFS
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 0) BFS(i, j);
            }
        }

        // 전부 카운트 완료
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                // 벽인경우 map의 상하좌우를 더한 뒤 + 1
                // 그런데 벽은 이미 1이므로 map의 상하좌우값만 더해주면 된다.
                // -> 반례발생
                // bfs집합을 만들고 맵에 저장, 집합을 표시한다.
                if(origin[i][j] == 1){
                    for(int k = 0; k < 4; k++){
                        int y = i + movY[k];
                        int x = j + movX[k];
                        if(y >= 0 && y < N
                        && x >= 0 && x < M
                        && map[y][x] != 1){
                            set.add(map[y][x]);
                        }
                    }
                    for(int num : set){
                        origin[i][j] += hash.get(num);
                    }
                }
                set.clear();
                bw.write((origin[i][j] % 10) + "");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    private static void BFS(int y, int x){
        int result = 1;
        Deque<int[]> deq = new ArrayDeque<>();
        map[y][x] = count;
        deq.offer(new int[]{y, x});

        while(!deq.isEmpty()){
            int[] nowYX = deq.poll();

            for(int i = 0; i < 4; i++){
                int nextY = nowYX[0] + movY[i];
                int nextX = nowYX[1] + movX[i];
                if(nextY >= 0 && nextY < N
                && nextX >= 0 && nextX < M
                && map[nextY][nextX] == 0){
                    map[nextY][nextX] = count;
                    result++;
                    deq.offer(new int[]{nextY, nextX});
                }
            }
        }

        hash.put(count, result);
        count++;
    }
}
