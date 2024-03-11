package algo.src.algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_SWT_벽돌깨기 {
	private static int result;
	private static int N, H, W;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		int[][] map = new int[H][W];

		result = 0;
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					result++;
			}
		}

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (map[j][i] != 0) {
					DFS(j, i, 1, map);
					break;
				} else if(j == H - 1 && map[j][i] == 0) {
					DFS(j, i, 1, map);
				}
			}
		}

		System.out.println(result);
	}

	// y, x에 공을 쐈을 때
	private static void DFS(int y, int x, int depth, int[][] map) {
		if (depth == 5) {
			int tmp = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] != 0) {
						tmp++;
					}
				}
			}
			result = Math.min(result, tmp);
			return;
		}
		
		int[][] tmpMap = new int[H][W];

		for (int i = 0; i < H; i++) {
			tmpMap[i] = map[i].clone();
		}

		Deque<int[]> deq = new ArrayDeque<>();
		deq.offer(new int[] { y, x, tmpMap[y][x] });
		tmpMap[y][x] = 0;

		while (!deq.isEmpty()) {
			int[] nowYX = deq.poll();

			for (int i = 0; i < W; i++) {
				if (nowYX[1] - nowYX[2] < i && i < nowYX[1] + nowYX[2] && tmpMap[y][i] != 0) {
					deq.offer(new int[] { y, i, tmpMap[y][i] });
					tmpMap[y][i] = 0;
				}
			}
			for (int i = 0; i < H; i++) {
				if (nowYX[0] - nowYX[2] < i && i < nowYX[0] + nowYX[2] && tmpMap[i][x] != 0) {
					deq.offer(new int[] { i, x, tmpMap[i][x] });
					tmpMap[i][x] = 0;
				}
			}
		}

		// 정리 - 밑에서부터 당겨야 한다
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (tmpMap[j][i] != 0) {
					int tmp = tmpMap[j][i];
					tmpMap[j][i] = 0;
					for (int k = j; k < H; k++) {
						if (k == H - 1 && tmpMap[H - 1][i] == 0) {
							tmpMap[H - 1][i] = tmp;
							break;
						} else if (tmpMap[k][i] != 0) {
							tmpMap[k - 1][i] = tmp;
							break;
						}
					}
				}
			}
		}

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (tmpMap[j][i] != 0) {
					DFS(j, i, depth + 1, tmpMap);
					break;
				} else if(j == H - 1 && map[j][i] == 0) {
					DFS(j, i, depth + 1, tmpMap);
				}
			}
		}
	}
}
