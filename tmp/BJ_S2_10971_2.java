package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_10971_2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int[][] result = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			result[0][i] = arr[0][i];
		}
		
		for(int i = 0; i < N - 1; i++) {
			for(int j = 1; j < N - 1; j++) {
				if(result[i][j] != 0) {
					for(int k = 1; k < N; k++) {
						if(arr[j][k] != 0) {
							result[i + 1][k] = result[i][j] + arr[i + 1][k];
						}
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(arr[i][0]);
		}
		
		
		int min = 1000000000;
		for(int i = 1; i < N; i++) {
			if(result[N - 1][i] != 0 && arr[i][0] != 0) {
				int tmp = result[N - 1][i] + arr[i][0];
				min = Math.min(min, tmp);
				System.out.println(result[N - 1][i] + " " + arr[i][0]);
			}
		}
		
		System.out.println(min);
		
	}
}