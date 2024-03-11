/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/11051
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11051.java
 @youtube
 @performance 15720KB, 96ms
 @category DP
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_11051 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][N + 1];
		
		for(int i = 0; i <= N; i++) {
			arr[i][0] = 1;
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				arr[i][j] = arr[i - 1][j] % 10007 + arr[i - 1][j - 1] % 10007;
			}
		}
		
		System.out.println(arr[N][K] % 10007);
	}
}
