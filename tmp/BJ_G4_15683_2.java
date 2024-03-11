package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G4_15683_2 {
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
	
	// 위, 오른쪽, 아래, 왼쪽 시계방향
	static int[] movY = {-1, 0, 1, 0};
	static int[] movX = {0, 1, 0, -1};
	
	// 회전방향표시
	static int[][] cctv1 = {{0}, {1}, {2}, {3}};
	static int[][] cctv2 = {{0, 2}, {1, 3}};
	static int[][] cctv3 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
	static int[][] cctv4 = {{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}};
	static int[][] cctv5 = {{0, 1, 2, 3}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		cctvCount = 0;
		
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(temp[j]);
				if(arr[i][j] >= 1 && arr[i][j] <= 5) {
					cctvCount++;
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
			for(int i = 0; i < cctv1.length; i++) {
				checkArr(cctv1[i], y, x, thisArr, count + 7);
				DFS(count + 1, thisArr);
				backArr(cctv1[i], y, x, thisArr, count + 7);
			}
		}
		else if(arr[y][x] == 2) {
			for(int i = 0; i < cctv2.length; i++) {
				checkArr(cctv2[i], y, x, thisArr, count + 7);
				DFS(count + 1, thisArr);
				backArr(cctv2[i], y, x, thisArr, count + 7);
			}
		}
		else if(arr[y][x] == 3) {
			for(int i = 0; i < cctv3.length; i++) {
				checkArr(cctv3[i], y, x, thisArr, count + 7);
				DFS(count + 1, thisArr);
				backArr(cctv3[i], y, x, thisArr, count + 7);
			}
		}
		else if(arr[y][x] == 4) {
			for(int i = 0; i < cctv4.length; i++) {
				checkArr(cctv4[i], y, x, thisArr, count + 7);
				DFS(count + 1, thisArr);
				backArr(cctv4[i], y, x, thisArr, count + 7);
			}
		}
		else if(arr[y][x] == 5) {
			for(int i = 0; i < cctv5.length; i++) {
				checkArr(cctv5[i], y, x, thisArr, count + 7);
				DFS(count + 1, thisArr);
				backArr(cctv5[i], y, x, thisArr, count + 7);
			}
		}
	}
	
	// u, d, r, l
	private static void checkArr(int[] move, int y, int x, int[][] arr, int mark){
		for(int i = 0; i < move.length; i++) {
			int nextY = y + movY[move[i]];
			int nextX = x + movX[move[i]];
			while(true) {
				if(nextY >= 0 && nextY < N
				&& nextX >= 0 && nextX < M
				&& arr[nextY][nextX] != 6) {
					if(arr[nextY][nextX] == 0) arr[nextY][nextX] = mark;
					nextY += movY[move[i]];
					nextX += movX[move[i]];
				} else break;
			}
		}
	}
	
	private static void backArr(int[] move, int y, int x, int[][] arr, int mark){
		for(int i = 0; i < move.length; i++) {
			int nextY = y + movY[move[i]];
			int nextX = x + movX[move[i]];
			while(true) {
				if(nextY >= 0 && nextY < N
				&& nextX >= 0 && nextX < M
				&& arr[nextY][nextX] != 6) {
					if(arr[nextY][nextX] == mark) arr[nextY][nextX] = 0;
					nextY += movY[move[i]];
					nextX += movX[move[i]];
				} else break;
			}
		}
	}
}
