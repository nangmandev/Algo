/**

 @author 한규준
 @since 2023-08-14
 @see https://www.acmicpc.net/problem/10844
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_10844.java
 @youtube
 @performance 11532KB, 76ms
 @category DP
 @note


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S1_10844 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[][] arr = new long[N][10];
		arr[0][0] = 1;
		for(int i = 1; i <= 9; i++) {
			arr[0][i] = 1;
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= 9; j++) {
				if(j == 0) {
					arr[i][j] = arr[i - 1][j + 1] % 1000000000;
				}
				else if(j == 9) {
					arr[i][j] = arr[i - 1][j - 1] % 1000000000;
				}
				else {
					arr[i][j] = arr[i - 1][j - 1] % 1000000000 + arr[i - 1][j + 1] % 1000000000;
					arr[i][j] = arr[i][j] % 1000000000;
				}
			}
		}
		
		long result = 0;
		for(int i = 1; i <= 9; i++) {
			result += arr[N - 1][i] % 1000000000;
			result = result % 1000000000;
		}
		
		System.out.println(result);
	}
}
