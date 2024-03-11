/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/1932
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1932.java
 @youtube
 @performance 29872KB, 236ms
 @category DP
 @note

위에서 내려오면서
합 갱신/저장

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_1932 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][][] arr = new int[N][N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j <= i; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		arr[0][0][1] = arr[0][0][0];
		int result = arr[0][0][0];
		for(int i = 0; i < N - 1; i++) {
			for(int j = 0; j <= i; j++) {
				arr[i + 1][j][1] = Math.max(arr[i][j][1] + arr[i + 1][j][0], arr[i + 1][j][1]);
				arr[i + 1][j + 1][1] = Math.max(arr[i][j][1] + arr[i + 1][j + 1][0], arr[i + 1][j + 1][1]);
				result = Math.max(result, Math.max(arr[i + 1][j][1], arr[i + 1][j + 1][1]));
			}
		}
		
		System.out.println(result);
	}
}