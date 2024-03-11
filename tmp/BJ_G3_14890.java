/**

 @author 한규준
 @since 2023-10-05
 @see https://www.acmicpc.net/problem/14890
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_14890.java
 @youtube
 @performance 13044KB, 104ms
 @category 구현
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_14890 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[][][] map = new int[N][N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;

		// 가로검사
		for (int i = 0; i < N; i++) {
			boolean TF = true;
			for (int j = 0; j < N - 1; j++) {
				if (Math.abs(map[i][j][0] - map[i][j + 1][0]) > 1)
					TF = false;
				else if (map[i][j][0] < map[i][j + 1][0]) {
					int mid = j + 1;
					if (mid - X >= 0 && map[i][mid - 1][1] == 0 && map[i][mid - X][1] == 0) {
						for (int k = mid - X + 1; k <= mid - 1; k++) {
							if (map[i][k][0] != map[i][k - 1][0]) {
								TF = false;
								break;
							}
						}
						if (TF) {
							for (int k = mid - X; k <= mid - 1; k++) {
								map[i][k][1] = 1;
							}
						}
					} else
						TF = false;
				} else if (map[i][j][0] > map[i][j + 1][0]) {
					int mid = j;
					if (mid + X < N) {
						for (int k = mid + 2; k <= mid + X; k++) {
							if (map[i][k][0] != map[i][k - 1][0]) {
								TF = false;
								break;
							}
						}
						if (TF) {
							for (int k = mid + 1; k <= mid + X; k++) {
								map[i][k][1] = 1;
							}
							j += X - 1;
						}
					} else
						TF = false;
				}
				if (!TF)
					break;
			}
			if (TF) {
				result++;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j][1] = 0;
			}
		}

		// 세로검사
		for (int i = 0; i < N; i++) {
			boolean TF = true;
			for (int j = 0; j < N - 1; j++) {
				if (Math.abs(map[j][i][0] - map[j + 1][i][0]) > 1)
					TF = false;
				else if (map[j][i][0] < map[j + 1][i][0]) {
					int mid = j + 1;
					if (mid - X >= 0 && map[mid - 1][i][1] == 0 && map[mid - X][i][1] == 0) {
						for (int k = mid - X + 1; k <= mid - 1; k++) {
							if (map[k][i][0] != map[k - 1][i][0]) {
								TF = false;
								break;
							}
						}
						if (TF) {
							for (int k = mid - X; k <= mid - 1; k++) {
								map[k][i][1] = 1;
							}
						}
					} else
						TF = false;
				} else if (map[j][i][0] > map[j + 1][i][0]) {
					int mid = j;
					if (mid + X < N) {
						for (int k = mid + 2; k <= mid + X; k++) {
							if (map[k][i][0] != map[k - 1][i][0]) {
								TF = false;
								break;
							}
						}
						if (TF) {
							for (int k = mid + 1; k <= mid + X; k++) {
								map[k][i][1] = 1;
							}
							j += X - 1;
						}
					} else
						TF = false;
				}
				if (!TF)
					break;
			}
			if (TF) {
				result++;
			}
		}

		System.out.println(result);
	}
}
