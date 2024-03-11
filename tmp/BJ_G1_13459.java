/**

 @author 한규준
 @since 2023-09-15
 @see https://www.acmicpc.net/problem/13459
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_13459.java
 @youtube
 @performance 11700KB, 80ms
 @category 구현, 시뮬레이션, BFS
 @note

1. yx좌표
2. 겹칠때 처리
3. 골처리
4. 몇회 움직였는지 처리
5. R, B 공 받을때 '.'으로 재초기화
6. visited가 두 공이 모두 같아야함

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G1_13459 {
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;

	static class ball {
		int y;
		int x;
		int count;
		int move;
	}

	static int[] movY = { -1, 1, 0, 0 };
	static int[] movX = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		ball redBall = new ball();
		ball blueBall = new ball();

		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'R') {
					redBall.y = i;
					redBall.x = j;
					redBall.count = 0;
					redBall.move = 0;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					blueBall.y = i;
					blueBall.x = j;
					blueBall.count = 0;
					blueBall.move = 0;
					map[i][j] = '.';
				}
			}
		}

		ball[] list = new ball[] { redBall, blueBall };

		Deque<ball[]> deq = new ArrayDeque<>();
		deq.offer(list);
		visited[redBall.y][redBall.x][blueBall.y][blueBall.x] = true;

		int result = 0;
		while (!deq.isEmpty()) {
			ball[] nowBall = deq.poll();
			// 받은 공이 골인한 경우
			// 파란공 골인한 경우, 빨간공 골인한 경우 그 외 경우
			for (int i = 0; i < 4; i++) {
				ball[] newList = moving(i, nowBall);

				// 골처리
				if (newList[1].y == -1) {
					continue;
				} else if (newList[0].y == -1) {
					if (newList[0].count <= 10) {
						result = 1;
						break;
					}
					continue;
				}

				// 공이 겹쳐있는경우 처리
				if (newList[0].y == newList[1].y && newList[0].x == newList[1].x) {
					if (newList[0].move > newList[1].move) {
						newList[0].y -= movY[i];
						newList[0].x -= movX[i];
					} else {
						newList[1].y -= movY[i];
						newList[1].x -= movX[i];
					}
				}

				// 방문처리
				if (!visited[newList[0].y][newList[0].x][newList[1].y][newList[1].x]) {
					visited[newList[0].y][newList[0].x][newList[1].y][newList[1].x] = true;
					deq.offer(newList);
				}
			}
			if (result == 1)
				break;
		}

		System.out.println(result);
	}

	private static ball[] moving(int direction, ball[] list) {
		ball[] tempList = new ball[2];
		ball redBall = new ball();
		ball blueBall = new ball();

		redBall.y = list[0].y;
		redBall.x = list[0].x;
		redBall.count = list[0].count;
		blueBall.y = list[1].y;
		blueBall.x = list[1].x;
		blueBall.count = list[1].count;

		tempList[0] = redBall;
		tempList[1] = blueBall;

		for (int i = 0; i < 2; i++) {
			while (true) {
				if (map[tempList[i].y][tempList[i].x] == 'O') {
					tempList[i].y = -1;
					tempList[i].x = -1;
					break;
				}
				int nextY = tempList[i].y + movY[direction];
				int nextX = tempList[i].x + movX[direction];

				if (nextY >= 0 && nextY < N && nextX >= 0 && nextX < M
						&& (map[nextY][nextX] == '.' || map[nextY][nextX] == 'O')) {
					// 일단 끝까지 이동시키는데
					// 중간에 골이 있으면 중지
					// 아니면 갱신, move 업데이트하고 진행
					tempList[i].y = nextY;
					tempList[i].x = nextX;
					tempList[i].move++;
				} else
					break;
			}
			tempList[i].count++;
		}

		return tempList;
	}
}
