/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/12833
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U1_12833.java
 @youtube
 @performance 12816KB, 108ms
 @category 비트마스킹
 @note

XOR연산은 모듈러를 수행해도 된다.

 */

package algo;

import java.util.Scanner;

public class BJ_U1_12833 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt() % 2;
		
		for(int i = 0 ; i < c; i++) {
			a = a ^ b;
		}
		
		System.out.println(a);
	}
}
