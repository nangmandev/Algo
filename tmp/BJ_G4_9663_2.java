package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_G4_9663_2 {
	static int N, result;
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] movY = {1, -1, 1, -1};
	static int[] movX = {1, -1, -1, 1};
	static int nextY, nextX;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N][N];
		result = 0;
		
		Queens(visited, 0, 0);
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
	}
	
	public static void Queens(boolean[][] visited, int y, int count) {
		if(count == N) {
			result += 1;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[y][i]) {
				boolean[][] v = new boolean[N][N];
				for(int k = 0; k < N; k++) {
					for(int j = 0; j < N; j++) {
						v[k][j] = visited[k][j];
					}
				}
				v[y][i] = true;
				for(int j = 0; j < N; j++) {
					v[y][j] = true;
					v[j][i] = true;
				}
				
				for(int j = 0; j < 4; j++) {
					int k = 0;
					while(true) {
						nextY = y + movY[j] * k;
						nextX = i + movX[j] * k;
						if(nextY >= 0 && nextY < N
						&& nextX >= 0 && nextX < N) {
							v[nextY][nextX] = true;
						}
						else break;
						k += 1;
					}
				}
				Queens(v, y + 1, count + 1);
			}
		}
	}
}
