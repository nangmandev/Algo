/**

 @author 한규준
 @since 2023-09-01
 @see https://www.acmicpc.net/problem/27960
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U3_27960.java
 @youtube
 @performance 11544KB, 80ms
 @category 비트마스킹
 @note

XOR연산

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_U3_27960 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		System.out.println(A ^ B);
	}
}