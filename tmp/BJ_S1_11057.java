/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/11057
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_11057.java
 @youtube
 @performance 11528KB, 76ms
 @category DP
 @note
 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_S1_11057 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int res=  0;
		
		int[][] arr = new int[N][10];
		Arrays.fill(arr[0], 1);
		
		for(int i = 1; i < N; i++) {
			arr[i][0] = 1;
			for(int j = 1; j < 10; j++) {
				arr[i][j] = (arr[i - 1][j] % 10007 + arr[i][j - 1] % 10007) % 10007;
			}
		}
		
		for(int i = 0; i < 10; i++) {
			res += arr[N - 1][i] % 10007;
		}
		
		System.out.println(res % 10007);
	}
}
