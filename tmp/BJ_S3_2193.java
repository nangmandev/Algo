/**

 @author 한규준
 @since 2023-09-05
 @see https://www.acmicpc.net/problem/2193
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_2193.java
 @youtube
 @performance 11492KB, 80ms
 @category DP
 @note

피보나치

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S3_2193 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N + 1];
		arr[0] = 0;
		arr[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		
		System.out.println(arr[N]);
	}
}