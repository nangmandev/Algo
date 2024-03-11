/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/2775
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U1_2775.java
 @youtube
 @performance 11540KB, 76ms
 @category DP
 @note

0층 모든 호, 모든 층 1호의 사람을 먼저 구한 다음 더하기
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_U1_2775 {
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());
			
			arr = new int[k + 1][n + 1];
			for(int i = 0; i <= n; i++) {
				arr[0][i] = i;
			}
			
			for(int i = 1; i <= k; i++) {
				arr[i][1] = 1;
			}
			
			for(int i = 1; i <= k; i++) {
				for(int j = 2; j <= n; j++) {
					arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
				}
			}
			
			bw.write(arr[k][n] + "\n");
		}
		bw.flush();
		bw.close();
	}
}
