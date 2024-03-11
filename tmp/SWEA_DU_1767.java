/**

 @author 한규준
 @since 2023-08-25
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_DU_1767.java
 @youtube
 @performance 22868kb, 771ms
 @category 재귀, 완전탐색, 가지치기
 @note

 1. 프로세서에서 바깥으로 연결하는 전선은 무조건 직선이어야 합니다.
 2. 한 프로세서에서 가능한 경우의 수는 위 아래 왼쪽 오른쪽 4가지입니다.
 3. 경우의 수를 모두 탐색하려면 프로세서당 4번 탐색을 진행하며 재귀를 진행합니다.
 4. 결과값은 짧은 길이보다 프로세서 수가 우선입니다.
 5. 최대 프로세서 수에 도달할 수 없다면 재귀탐색을 종료합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_DU_1767 {
	static int N;
	static int[][] arr;
	static int[][] processors;
	static int result;
	static int maxConnected;
	static int proCount;
	
	static int[] movY = {-1, 1, 0, 0};
	static int[] movX = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			result = Integer.MAX_VALUE;
			proCount = 0;
			maxConnected = 0;

			// 탐색을 진행하기 위해 필요한 배열입니다.
			arr = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					// 각 프로세서마다 탐색을 진행해야 하기 때문에 프로세서 수를 카운트합니다.
					if(arr[i][j] == 1) proCount++;
				}
			}

			// 카운트한 프로세서 수로 프로세서 배열을 만들고 저장합니다.
			processors = new int[proCount][2];
			
			int idx = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] == 1) {
						processors[idx][0] = i;
						processors[idx++][1] = j;
					}
				}
			}
			
			// 초기화 완료
			// 4방향으로 재귀탐색을 진행합니다.
			// 배열 복사를 줄이기 위해 한 번 탐색이 끝난 이후에 다시 맵을 되돌립니다.
			for(int i = 0; i < 4; i++) {
				DFS(i, 0, arr, 0, 0);
				rollBack(0, i, arr);
			}
			
			bw.write("#" + tc + " " + result + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static void DFS(int direction, int level, int[][] arr, int count, int connected) {
		// 프로세서를 모두 탐색하면 종료합니다.
		if(level == proCount) {
			// 연결된 프로세서 수가 최대크기라면 값을 비교 후 갱신합니다.
			if(connected == maxConnected) {
				result = Math.min(result, count);
				maxConnected = connected;
			}
			// 연결된 프로세서 수가 최대크기보다 크다면 값을 갱신합니다.
			else if(connected > maxConnected) {
				result = count;
				maxConnected = connected;
			}
			return;
		}
		// 종료조건
		// 어떻게 해도 최대 프로세서에 도달할 수 없다면, 쓸모없는 값입니다.
		if(maxConnected > connected + proCount - level) return;

		// 현재 방향으로의 탐색을 시작한 뒤의 전선 수와 프로세서 수를 받습니다.
		int newCount = count + move(level, direction, arr);
		int newConnected = connected + newCount / 1000;

		// 4방향 탐색을 진행합니다.
		// 다음 레벨이 프로세서 최대수라면 DFS는 거기서 끝이기 때문에 해당 경우 롤백하지 않습니다.
		for(int i = 0; i < 4; i++) {
			DFS(i, level + 1, arr, newCount % 1000, newConnected);
			if(level + 1 != proCount) rollBack(level + 1, i, arr);
		}
		
	}


	// 탐색 메서드입니다.
	private static int move(int nth, int direction, int[][] arr) {
		// 프로세서별로 좌표를 받아오고 전선 카운트를 초기화합니다.
		int y = processors[nth][0] + movY[direction];
		int x = processors[nth][1] + movX[direction];
		int count = 0;
		
		while(true) {
			// 좌표가 범위를 벗어나면 프로세서가 연결된 것입니다.
			// 따라서, 카운트값에 1000을 더해 리턴합니다.
			// 카운트의 최대값은 N이기 때문에, 1000을 더해 넘겨도 됩니다.
			if(y < 0 || x < 0 || y >= N || x >= N) {
				return count + 1000;
			}

			// 빈칸이 아니라면 다시 되돌립니다.
			else if(arr[y][x] != 0) {
				rollBack(nth, direction, arr);
				return 0;
			}

			// 빈칸이라면 현재 레벨을 visited로 표시합니다.
			// 현재 레벨을 표시하기 때문에 지울 때도 전선이 겹치는 구간을 걱정하지 않아도 됩니다.
			else if(arr[y][x] == 0) {
				count++;
				arr[y][x] = nth + 2;
				y += movY[direction];
				x += movX[direction];
			}
		}
	}

	// 전선을 다시 제거하는 메서드입니다.
	// 전선을 칠하는 과정과 비슷하게 동작합니다.
	private static void rollBack(int nth, int direction, int[][] arr) {
		// 프로세서의 위치를 받아옵니다.
		int y = processors[nth][0] + movY[direction];
		int x = processors[nth][1] + movX[direction];
		
		while(true) {
			// 맵 밖으로 넘어가면 종료합니다.
			if(y < 0 || x < 0 || y >= N || x >= N) {
				return;
			}
			// 현재 깔아둔 전선이 아니라면 끝냅니다.
			else if(arr[y][x] != nth + 2) {
				return;
			}
			// 현재 깔아둔 전선이라면 다시 지웁니다.
			else if(arr[y][x] == nth + 2) {
				arr[y][x] = 0;
				y += movY[direction];
				x += movX[direction];
			}
		}
	}
}
