/**

 @author 한규준
 @since 2023-10-04
 @see https://www.acmicpc.net/problem/16137
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_16137.java
 @youtube
 @performance 11652KB, 80ms
 @category BFS, 구현
 @note

3가지 경우의 수
그냥 땅인경우는 그대로 진행
절벽인경우 오작교 만들고 flag올리고 시간대기
원래 오작교인경우 flag올리고 시간대기

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G2_16137 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][N];
		boolean[][][] visited = new boolean[N][N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 일반적인 땅이면 진행, 오작교 건넘 플래그 지우기
		// 2. 절벽이면 가로세로 교차하는지 확인후 오작교, 오작교 지음 플래그, 오작교 건넘 플래그
		// 3. 이미 오작교면 오작교 건넘 플래그
		
		// y, x, count, construct, cross
		int[] start = {0, 0, 0, 0, 0};
		Deque<int[]> deq = new ArrayDeque<>();
		visited[0][0][0] = true;
		deq.offer(start);
		
		int[] movY = {-1, 1, 0, 0};
		int[] movX = {0, 0, -1, 1};
		
		int[][] checkYX = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
		
		int result = 0;
		while(!deq.isEmpty()) {
			int[] nowYX = deq.poll();
			if(map[nowYX[0]][nowYX[1]] > 1) {
				if(nowYX[2] % map[nowYX[0]][nowYX[1]] != 0) {
					deq.offer(new int[] {nowYX[0], nowYX[1], nowYX[2] + 1, nowYX[3], nowYX[4]});
					continue;
				}
			}
			
			// 0인경우 M까지 대기
			if(map[nowYX[0]][nowYX[1]] == 0) {
				if(nowYX[2] % T != 0) {
					deq.offer(new int[] {nowYX[0], nowYX[1], nowYX[2] + 1, nowYX[3], nowYX[4]});
					continue;
				}
			}
			
			if(nowYX[0] == (N - 1) && nowYX[1] == (N - 1)) {
				result = nowYX[2];
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nextY = nowYX[0] + movY[i];
				int nextX = nowYX[1] + movX[i];
				if(nextY >= 0 && nextY < N
				&& nextX >= 0 && nextX < N
				&& !visited[nextY][nextX][nowYX[3]]) {
					// 1. 일반적인 땅인경우
					if(map[nextY][nextX] == 1) {
						visited[nextY][nextX][nowYX[3]] = true;
						deq.offer(new int[] {nextY, nextX, nowYX[2] + 1, nowYX[3], 0});
					}
					// 2. 2이상의 수인경우
					else if(map[nextY][nextX] > 1) {
						// 연속으로 건널 수는 없다.
						if(nowYX[4] == 0) {
							visited[nextY][nextX][nowYX[3]] = true;
							deq.offer(new int[] {nextY, nextX, nowYX[2] + 1, nowYX[3], 1});
						}
					}
					// 3. 절벽인경우
					else {
						// 직전에 건너지 않았고 construct flag == 0
						if(nowYX[3] == 0 && nowYX[4] == 0) {
							boolean canConstruct = true;
							for(int j = 0; j < 4; j++) {
								int k = (j + 1) % 4;
								int[] tmpYX1 = checkYX[j];
								int[] tmpYX2 = checkYX[k];
								int nY1 = nextY + tmpYX1[0];
								int nX1 = nextX + tmpYX1[1];
								int nY2 = nextY + tmpYX2[0];
								int nX2 = nextX + tmpYX2[1];
								if(nY1 >= 0 && nY1 < N
								&& nX1 >= 0 && nX1 < N
								&& nY2 >= 0 && nY2 < N
								&& nX2 >= 0 && nX2 < N
								&& map[nY1][nX1] == 0
								&& map[nY2][nX2] == 0) {
									canConstruct = false;
									break;
								}
							}
							
							if(canConstruct && !visited[nextY][nextX][1]) {
								visited[nextY][nextX][1] = true;
								deq.offer(new int[] {nextY, nextX, nowYX[2] + 1, 1, 1});
							}
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
