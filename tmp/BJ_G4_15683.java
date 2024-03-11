/**

 @author 한규준
 @since 2023-08-22
 @see https://www.acmicpc.net/problem/15683
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_15683.java
 @youtube
 @performance 78068KB, 412ms
 @category 구현, 백트래킹, DFS
 @note

빡구현

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G4_15683 {
	static int N, M, cctvCount;
	static int[][] arr;
	static CCTV[] cctvs;
	static int minCount = Integer.MAX_VALUE;
	
	static class CCTV{
		int y;
		int x;
		public CCTV(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int[] movY = {1, -1, 0, 0};
	static int[] movX = {0, 0, 1, -1};
	static char[] direction = {'u', 'd', 'l', 'r'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		cctvCount = 0;
		int zeroCount = 0;
		
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
				if(arr[i][j] >= 1 && arr[i][j] <= 5) {
					cctvCount++;
				}
				else if(arr[i][j] == 0) {
					zeroCount++;
				}
			}
		}
		
		cctvs = new CCTV[cctvCount];
		int cctvsIdx = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(arr[i][j] >= 1 && arr[i][j] <= 5) {
					cctvs[cctvsIdx++] = new CCTV(i, j);
				}
			}
		}
		
		// 초기화 완료
		DFS(0, arr);
		// 0번째부터 시작
		
		if(minCount != Integer.MAX_VALUE) System.out.println(minCount);
		else System.out.println(0);
	}
	
	private static void DFS(int count, int[][] tempArr) {
		int[][] thisArr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				thisArr[i][j] = tempArr[i][j];
			}
		}
		
		if(count == cctvCount) {
			int tempCount = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(thisArr[i][j] == 0) tempCount++;
				}
			}
			minCount = Math.min(minCount, tempCount);
			return;
		}
		
		CCTV nowCCTV = cctvs[count];
		int y = nowCCTV.y;
		int x = nowCCTV.x;
		
		if(arr[y][x] == 1) {
			for(int i = 0; i < 4; i++) {
				checkArr(direction[i], thisArr, y, x, count + 7);
				DFS(count + 1, thisArr);
				backArr(direction[i], thisArr, y, x, count + 7);
			}
		}
		// u, d, l, r
		else if(arr[y][x] == 2) {
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[1], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[1], thisArr, y, x, count + 7);
			
			checkArr(direction[2], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[2], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
		}
		else if(arr[y][x] == 3) {
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[2], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[2], thisArr, y, x, count + 7);
			
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
			
			checkArr(direction[1], thisArr, y, x, count + 7);
			checkArr(direction[2], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[1], thisArr, y, x, count + 7);
			backArr(direction[2], thisArr, y, x, count + 7);
			
			checkArr(direction[1], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[1], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
		}
		else if(arr[y][x] == 4) {
			
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[1], thisArr, y, x, count + 7);
			checkArr(direction[2], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[1], thisArr, y, x, count + 7);
			backArr(direction[2], thisArr, y, x, count + 7);
			
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[1], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[1], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
			
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[2], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[2], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
			
			checkArr(direction[1], thisArr, y, x, count + 7);
			checkArr(direction[2], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[1], thisArr, y, x, count + 7);
			backArr(direction[2], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
		}
		else if(arr[y][x] == 5) {
			checkArr(direction[0], thisArr, y, x, count + 7);
			checkArr(direction[1], thisArr, y, x, count + 7);
			checkArr(direction[2], thisArr, y, x, count + 7);
			checkArr(direction[3], thisArr, y, x, count + 7);
			DFS(count + 1, thisArr);
			backArr(direction[0], thisArr, y, x, count + 7);
			backArr(direction[1], thisArr, y, x, count + 7);
			backArr(direction[2], thisArr, y, x, count + 7);
			backArr(direction[3], thisArr, y, x, count + 7);
		}
	}
	
	// u, d, r, l
	private static int[][] checkArr(char a, int[][] arr, int y, int x, int mark){
		if(a == 'u') {
			int nextY = y - 1;
			while(true) {
				if(nextY >= 0 && nextY < N
				&& arr[nextY][x] != 6) {
					if(arr[nextY][x] == 0) arr[nextY][x] = mark;
					nextY -= 1;
				} else break;
			}
		}
		else if(a == 'd') {
			int nextY = y + 1;
			while(true) {
				if(nextY >= 0 && nextY < N
				&& arr[nextY][x] != 6) {
					if(arr[nextY][x] == 0) arr[nextY][x] = mark;
					nextY += 1;
				} else break;
			}
		}
		else if(a == 'l') {
			int nextX = x - 1;
			while(true) {
				if(nextX >= 0 && nextX < M
				&& arr[y][nextX] != 6) {
					if(arr[y][nextX] == 0) arr[y][nextX] = mark;
					nextX -= 1;
				} else break;
			}
		}
		else if(a == 'r') {
			int nextX = x + 1;
			while(true) {
				if(nextX >= 0 && nextX < M
				&& arr[y][nextX] != 6) {
					if(arr[y][nextX] == 0) arr[y][nextX] = mark;
					nextX += 1;
				} else break;
			}
		}
		return arr;
	}
	
	private static int[][] backArr(char a, int[][] arr, int y, int x, int mark){
		if(a == 'u') {
			int nextY = y - 1;
			while(true) {
				if(nextY >= 0 && nextY < N
				&& arr[nextY][x] != 6) {
					if(arr[nextY][x] == mark) arr[nextY][x] = 0;
					nextY -= 1;
				} else break;
			}
		}
		else if(a == 'd') {
			int nextY = y + 1;
			while(true) {
				if(nextY >= 0 && nextY < N
				&& arr[nextY][x] != 6) {
					if(arr[nextY][x] == mark) arr[nextY][x] = 0;
					nextY += 1;
				} else break;
			}
		}
		else if(a == 'l') {
			int nextX = x - 1;
			while(true) {
				if(nextX >= 0 && nextX < M
				&& arr[y][nextX] != 6) {
					if(arr[y][nextX] == mark) arr[y][nextX] = 0;
					nextX -= 1;
				} else break;
			}
		}
		else if(a == 'r') {
			int nextX = x + 1;
			while(true) {
				if(nextX >= 0 && nextX < M
				&& arr[y][nextX] != 6) {
					if(arr[y][nextX] == mark) arr[y][nextX] = 0;
					nextX += 1;
				} else break;
			}
		}
		return arr;
	}
}
