/**

 @author 한규준
 @since 2023-08-22
 @see https://www.acmicpc.net/problem/17070
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_17070.java
 @youtube
 @performance 14316KB, 268ms
 @category DFS, BFS, DP
 @note

현재 풀이는 DFS
어차피 파이프의 뒷부분은 앞부분 경로를 따라오기 때문에 신경쓸 필요가 없다.
파이프 앞부분 경로만 생각하면 된다.
각 방향에서 뻗어나갈 수 있는 조건만 생각해서 DFS돌리고, 목적지 도달한 경우만 생각

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G5_17070 {
	static int N, arr[][];
	static int count = 0;
	
	// 아래, 오른쪽, 대각선
	static int[] movY = {1, 0, 1};
	static int[] movX = {0, 1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		// 초기화 완료
		DFS(0, 1, 0);
		
		System.out.println(count);
	}
	
	public static void DFS(int y, int x, int degree) {
		if(y == N - 1 && x == N - 1) {
			count++;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			int nextY = y + movY[i];
			int nextX = x + movX[i];
			if(nextY >= 0 && nextY < N
			&& nextX >= 0 && nextX < N
			&& arr[nextY][nextX] == 0) {
				// 대각선 조건 추가. 대각선은 어떠한 상태더라도 갈 수 있다.
				if(i == 2 && arr[nextY][x] == 0 && arr[y][nextX] == 0) {
					DFS(nextY, nextX, 45);
				}
				// 아래로 이동하는 경우는 대각선/아래방향
				else if(i == 0 && (degree == 45 || degree == 90)) {
					DFS(nextY, nextX, 90);
				}
				// 우측으로 이동하는 경우는 대각선/우측방향
				else if(i == 1 && (degree == 45 || degree == 0)) {
					DFS(nextY, nextX, 0);
				}
			}
				
		}
	}
}
