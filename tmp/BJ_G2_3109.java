/**

 @author 한규준
 @since 2023-08-16
 @see https://www.acmicpc.net/problem/3109
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_3109.java
 @youtube
 @performance 66604KB, 404ms
 @category DFS, 메모이제이션
 @note

위에서 아래로, 도착지까지 한번씩만 탐색

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G2_3109 {
	static int[][] arr;
	static int[][] visited;
	static int result, R, C;
	static int tempCount;
	static int find;
	
	static int[] movY = {-1, 0, 1};
	static int[] movX = {1, 1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		visited = new int[R][C];
		
		result = 0;
		
		for(int i = 0; i < R; i++) {
			String temp = br.readLine();
			for(int j = 0; j < C; j++) {
				if(temp.charAt(j) == '.') {
					arr[i][j] = 0;
				}
				else arr[i][j] = -1;
			}
		}
		
		// 초기화 끝
		int idx = 0;
		for(int i = 0; i < R; i++) {
			DFS(idx, i, 0);
			idx = find;
		}
		
		bw.write(find + "\n");
		bw.flush();
		bw.close();
		
	}
	
	public static int DFS(int num, int y, int x) {
		int temp = 0;
		for(int i = 0; i < 3; i++) {
			int nextY = y + movY[i];
			int nextX = x + movX[i];
			
			if(nextY >= 0 && nextY < R
			&& nextX >= 0 && nextX < C
			&& visited[nextY][nextX] == 0
			&& arr[nextY][nextX] == 0) {
				if(nextX == C - 1) {
					if(num == find) {
					    if(visited[nextY][nextX] == 0) visited[nextY][nextX] = 1;
					    temp = 2;
					    find += 1;
					    break;
					}
				}
				else if(num == find) temp = DFS(num, nextY, nextX);
			}
		}
		visited[y][x] = temp;
		return visited[y][x] + 1;
	}
}
