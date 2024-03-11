/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/1010
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S5_1010.java
 @youtube
 @performance 13452KB, 116ms
 @category DP, 조합
 @note

조합을 DP로 구하면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S5_1010 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[M + 1][M + 1];
			
			for(int i = 0; i <= M; i++) {
				for(int j = 0; j <= i; j++) {
					if(j == 0 || j == i) {
						arr[i][j] = 1;
					}
					else {
						arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
					}
				}
			}
			System.out.println(arr[M][N]);
		}
	}
}