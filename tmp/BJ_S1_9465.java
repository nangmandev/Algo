/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/9465
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_2493.java
 @youtube
 @performance 152432KB, 920ms
 @category DP
 @note

 */
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_9465 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][2];
			int[][] res = new int[N][2];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[j][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[j][1] = Integer.parseInt(st.nextToken());
			}
			
			if(N == 1) {
				System.out.println(Math.max(arr[0][0], arr[0][1]));
			}
			else if(N == 2) {
				System.out.println(Math.max(arr[0][0] + arr[1][1], arr[0][1] + arr[1][0]));
			}
			else {
				res[0][0] = arr[0][0];
				res[0][1] = arr[0][1];
				res[1][0] = arr[0][1] + arr[1][0];
				res[1][1] = arr[0][0] + arr[1][1];
				
				for(int j = 2; j < N; j++) {
					res[j][0] = Math.max(res[j - 1][1], Math.max(res[j - 2][0], res[j - 2][1])) + arr[j][0];
					res[j][1] = Math.max(res[j - 1][0], Math.max(res[j - 2][0], res[j - 2][1])) + arr[j][1];
				}
				
				System.out.println(Math.max(res[N - 1][0], res[N - 1][1]));
			}
			
		}
	}
}
