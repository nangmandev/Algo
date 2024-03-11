/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/2839
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_2839.java
 @youtube
 @performance 13532KB, 112ms
 @category DP
 @note

범위만큼 배열을 만들고
점화식을 세워서 리턴

 */

package algo;

import java.util.Scanner;

public class BJ_S4_2839 {
	static int[] arr;
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		arr = new int[5001];
		
		arr[3] = 1;
		arr[5] = 1;
		
		int result = dp(N);
		
		if(result > 0) System.out.println(result);
		else System.out.println(-1);
	}
	
	private static int dp(int N) {
		if(N == 3 || N == 5) return 1;
		if(N < 3) return -1;
		if(arr[N] != 0) return arr[N];
		
		int a, b;
		if(N >= 3) {
			arr[N - 3] = dp(N - 3);
			a = arr[N - 3];
		}
		else a = -1;
		if(N >= 5) {
			arr[N - 5] = dp(N - 5);
			b = arr[N - 5];
		}
		else b = -1;
		
		if(a == -1 && b == -1) {
			return -1;
		}
		else if(a == -1 || b == -1) {
			return Math.max(a, b) + 1;
		}
		else return Math.min(a, b) + 1;
	}
}
