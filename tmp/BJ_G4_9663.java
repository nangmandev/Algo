package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G4_9663 {
	static int N, result;
	static int[][] arr;
	static int[] movY = {1, -1, 1, -1};
	static int[] movX = {1, -1, -1, 1};
	static int nextY, nextX;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		result = 0;
		
		backT(0, 0);
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		
	}
	
	static void backT(int y, int count) {
		if(count == N) {
			result++;
			return;
		}
		for(int j = 0; j < N; j++) {
			if(checkQ(y, j)) {
				count++;
			    backT(y + 1, count);
				arr[y][j] = 0;
				count--;
			}
		}
	}
	
	static boolean checkQ(int y, int x) {
		// y축
		for(int i = 0; i < N; i++) {
			if(arr[i][x] == 1) {
				return false;
			}
		}
		
		// 대각선
		for(int i = 0; i < 4; i++) {
			int k = 1;
			while(true) {
				nextY = y + (movY[i] * k);
				nextX = x + (movX[i] * k);
				if(nextY >= 0 && nextY < N
				&& nextX >= 0 && nextX < N) {
					if(arr[nextY][nextX] == 1) {
						return false;
					}
				}
				else break;
				k += 1;
			}
		}

		arr[y][x] = 1;
		return true;
	}
}
