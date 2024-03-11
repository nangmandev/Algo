/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/9095
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_9095.java
 @youtube
 @performance 12848KB, 108ms
 @category DP
 @note

수가 얼마 되지 않고 규칙이 있으므로
미리 구하고 출력

 */

package algo;

import java.util.Scanner;

public class BJ_S3_9095 {
	static int[] arr = new int[12];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr[1] = 1;
		arr[2] = 2;
		arr[3] = 4;
		
		for(int i = 4; i <= 11; i++) {
			arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
		}
		
		int T = sc.nextInt();
		
		for(int i = 1; i <= T; i++) {
			int n = sc.nextInt();
			System.out.println(arr[n]);
		}
	}
}
