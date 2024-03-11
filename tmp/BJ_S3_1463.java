/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/1463
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_1463.java
 @youtube
 @performance 17084KB, 132ms
 @category DP
 @note

재귀를 통해 DP를 구하면 메모리/스택오버
그냥 수열을 진행하며 구해야 한다
 */

package algo;

import java.util.Scanner;

public class BJ_S3_1463 {
	static int[] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();
		
		arr = new int[1000001];
		arr[1] = 0;
		arr[2] = 1;
		arr[3] = 1;
		
		for(int i = 4; i <= X; i++) {
			if(i % 3 == 0 && i % 2 == 0) {
				arr[i] = Math.min(Math.min(arr[i / 3], arr[i / 2]), arr[i - 1]);
			}
			else if(i % 3 == 0) {
				arr[i] = Math.min(arr[i / 3], arr[i - 1]);
			}
			else if(i % 2 == 0) {
				arr[i] = Math.min(arr[i / 2], arr[i - 1]);
			}
			else arr[i] = arr[i - 1];
			arr[i] += 1;
		}
		
		System.out.println(arr[X]);
	}
}
