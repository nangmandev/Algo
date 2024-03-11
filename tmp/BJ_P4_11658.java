/**

 @author 한규준
 @since 2023-08-29
 @see https://www.acmicpc.net/problem/11658
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P4_11658.java
 @youtube
 @performance 149216KB, 1880ms
 @category DP, 누적합
 @note

 행별로 세그먼트트리 구현했으나 시간초과 -> O(NlogN)
 다시 생각해보니 예전에 풀었던 누적합과 같음
 행 누적합으로 풀이

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_P4_11658 {
	static int N, M;
	static long[][] arr, modify;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new long[N][N];
		modify = new long[N][N];

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(j == 0) modify[i][j] = arr[i][j];
				else modify[i][j] = modify[i][j - 1] + arr[i][j];
			}
		}



		int a, b, c, d, e;
		for(int i = 0; i < M; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			if(a == 0){
				b = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;
				d = Integer.parseInt(st.nextToken());
				update(b, c, d);
			}
			else {
				b = Integer.parseInt(st.nextToken()) - 1;
				c = Integer.parseInt(st.nextToken()) - 1;
				d = Integer.parseInt(st.nextToken()) - 1;
				e = Integer.parseInt(st.nextToken()) - 1;
				bw.write(getSum(b, c, d, e) + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static void update(int y, int x, int val){
		arr[y][x] = val;
		if(x == 0){
			modify[y][0] = val;
			for(int i = 1; i < N; i++){
				modify[y][i] = modify[y][i - 1] + arr[y][i];
			}
		}
		else {
			for(int i = x; i < N; i++){
				modify[y][i] = modify[y][i - 1] + arr[y][i];
			}
		}
	}

	private static long getSum(int y1, int x1, int y2, int x2){
		long sum = 0;
		for(int i = y1; i <= y2; i++){
			sum += modify[i][x2];
		}
		if(x1 != 0){
			for(int i = y1; i <= y2; i++){
				sum -= modify[i][x1 - 1];
			}
		}
		return sum;
	}
}
