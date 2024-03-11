/**

 @author 한규준
 @since 2023-09-29
 @see https://www.acmicpc.net/problem/1400
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_1400.java
 @youtube
 @performance 11824KB, 80ms
 @category BFS
 @note

띄어쓰기

 */

package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G2_1400 {
    static class TL{
        int th;
        int nowSecond;
        char direction;
        int hor;
        int ver;
        public TL(int th, int nowSecond,char direction, int hor, int ver){
            this.th = th;
            this.nowSecond = nowSecond;
            this.direction = direction;
            this.hor = hor;
            this.ver = ver;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            char[][] map = new char[N][M];
            boolean[][] visited = new boolean[N][M];

            int[] startYX = new int[3];
            int TLCount = 0;
            for (int i = 0; i < N; i++) {
                String temp = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = temp.charAt(j);
                    if (map[i][j] == 'A') {
                        startYX[0] = i;
                        startYX[1] = j;
                        visited[i][j] = true;
                        map[i][j] = '#';
                    } else if (map[i][j] >= '0' && map[i][j] <= '9') {
                        TLCount++;
                    }
                }
            }

            ArrayList<TL> arr = new ArrayList<>();
            for (int i = 0; i < TLCount; i++) {
                st = new StringTokenizer(br.readLine());
                int th = Integer.parseInt(st.nextToken());
                int nowSecond = 1;
                char direction = st.nextToken().charAt(0);
                int hor = Integer.parseInt(st.nextToken());
                int ver = Integer.parseInt(st.nextToken());
                arr.add(new TL(th, nowSecond, direction, hor, ver));
            }

            int result = Integer.MAX_VALUE;
            Deque<int[]> deq = new ArrayDeque<>();
            deq.offer(startYX);

            int[] movY = {-1, 1, 0, 0};
            int[] movX = {0, 0, -1, 1};

            while (!deq.isEmpty()) {
                int count = deq.size();
                while (count != 0) {
                    int[] nowYX = deq.poll();

                    for (int i = 0; i < 4; i++) {
                        int nextY = nowYX[0] + movY[i];
                        int nextX = nowYX[1] + movX[i];
                        int nextSecond = nowYX[2] + 1;
                        if (nextY >= 0 && nextY < N
                                && nextX >= 0 && nextX < M
                                && !visited[nextY][nextX]
                                && map[nextY][nextX] != '.') {

                            if (map[nextY][nextX] == 'B') {
                                result = Math.min(result, nextSecond);
                            } else if (map[nextY][nextX] == '#') {
                                deq.offer(new int[]{nextY, nextX, nextSecond});
                                visited[nextY][nextX] = true;
                            } else {
                                int tlNum = map[nextY][nextX] - '0';
                                TL tls = arr.get(tlNum);

                                if (nowYX[1] != nextX && tls.direction == '-') {
                                    deq.offer(new int[]{nextY, nextX, nextSecond});
                                    visited[nextY][nextX] = true;
                                } else if (nowYX[0] != nextY && tls.direction == '|') {
                                    deq.offer(new int[]{nextY, nextX, nextSecond});
                                    visited[nextY][nextX] = true;
                                } else {
                                    deq.offer(new int[]{nowYX[0], nowYX[1], nextSecond});
                                }
                            }
                        }
                    }
                    count--;
                }
                for (int i = 0; i < TLCount; i++) {
                    TL tls = arr.get(i);
                    if (tls.direction == '-' && tls.nowSecond == tls.hor) {
                        tls.direction = '|';
                        tls.nowSecond = 1;
                    } else if (tls.direction == '|' && tls.nowSecond == tls.ver) {
                        tls.direction = '-';
                        tls.nowSecond = 1;
                    } else tls.nowSecond++;
                }
            }
            String trash = br.readLine();
            if(result == Integer.MAX_VALUE) bw.write("impossible\n");
            else bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
