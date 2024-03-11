/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/1600
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1600.java
 @youtube
 @performance 64392KB, 516ms
 @category BFS, 다차원 visited
 @note

스킬 사용 가능 횟수만큼 visited를 추가해서
상태별로 visited가 겹치지 않게 처리

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G3_1600 {
	static int K, W, H, result;
	static int[][] arr;
	static int[][][] visited;

	// 오공
	static int[] movY = { -1, 1, 0, 0 };
	static int[] movX = { 0, 0, -1, 1 };

	// 헤카림
	static int[] hY = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] hX = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		result = Integer.MAX_VALUE;
		
		arr = new int[H][W];
		visited = new int[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 초기화 완료
		BFS();
		
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result - 1);
	}

	private static void BFS() {
		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] { 0, 0, 0 });
		visited[0][0][0] = 1;

		while (!deq.isEmpty()) {
			int[] yx = deq.poll();
			if(yx[0] == H - 1 && yx[1] == W - 1) {
				result = Math.min(result, visited[yx[0]][yx[1]][yx[2]]);
			}

			// 오공 폼 이동
			for (int i = 0; i < 4; i++) {
				int nextY = yx[0] + movY[i];
				int nextX = yx[1] + movX[i];
				if(nextY >= 0 && nextY < H
				&& nextX >= 0 && nextX < W
				&& arr[nextY][nextX] == 0
				&& visited[nextY][nextX][yx[2]] == 0) {
					visited[nextY][nextX][yx[2]] = visited[yx[0]][yx[1]][yx[2]] + 1;
					deq.offer(new int[] { nextY, nextX, yx[2] });
				}
			}

			// 헤카림 폼 이동
			if (yx[2] < K) {
				for (int i = 0; i < 8; i++) {
					int nextY = yx[0] + hY[i];
					int nextX = yx[1] + hX[i];
					if(nextY >= 0 && nextY < H
					&& nextX >= 0 && nextX < W
					&& arr[nextY][nextX] == 0
					&& visited[nextY][nextX][yx[2] + 1] == 0) {
						visited[nextY][nextX][yx[2] + 1] = visited[yx[0]][yx[1]][yx[2]] + 1;
						deq.offer(new int[] { nextY, nextX, yx[2] + 1 });
					}
				}
			}
		}
	}
}