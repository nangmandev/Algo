/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/1904
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_1904.java
 @youtube
 @performance 19312KB, 104ms
 @category DP 문제읽기
 @note

피보나치
 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S3_1904 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] arr = new long[N + 1];
		arr[0] = 1;
		arr[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] % 15746 + arr[i - 2] % 15746;
		}
		
		System.out.println(arr[N] % 15746);
	}
}
