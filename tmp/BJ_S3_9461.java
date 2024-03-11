/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/9461
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_9461.java
 @youtube
 @performance 11472KB, 84ms
 @category DP
 @note

일반 수열문제
i = (i - 2) + (i - 3)
다만, int형 사용시 오버플로우 조심할것

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_9461 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		long[] arr = new long[100];
		arr[0] = 1; arr[1] = 1; arr[2] = 1;
		
		for(int i = 3; i < 100; i++) {
			arr[i] = arr[i - 2] + arr[i - 3];
		}
		
		for(int i = 0; i < T; i++) {
			System.out.println(arr[Integer.parseInt(br.readLine()) - 1]);
		}
	}
}