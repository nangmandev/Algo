/**

 @author 한규준
 @since 2023-09-04
 @see https://www.acmicpc.net/problem/2156
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_2156.java
 @youtube
 @performance 12940KB, 108ms
 @category DP
 @note

경우의수

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S1_2156 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] res = new int[n + 1];
		int max = 0;
		
		if(n == 1) {
			max = arr[1];
		}
		else if(n == 2) {
			max = arr[1] + arr[2];
		}
		else {
			res[1] = arr[1];
			res[2] = arr[1] + arr[2];
			max = res[2];
		}
		
		for(int i = 3; i <= n; i++) {
			res[i] = Math.max(res[i - 2] + arr[i], res[i - 3] + arr[i - 1] + arr[i]);
			res[i] = Math.max(res[i], res[i - 1]);
			max = Math.max(res[i], max);
		}
		
		System.out.println(max);
	}
}
