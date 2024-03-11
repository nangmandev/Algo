/**

 @author 한규준
 @since 2023-09-01
 @see https://www.acmicpc.net/problem/25166
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U1_25166.java
 @youtube
 @performance 11444KB, 76ms
 @category 비트마스킹
 @note

남은금액 비트 비교(동전이 최대 1개씩이므로)

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_U1_25166 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		if(S < 1024) System.out.println("No thanks");
		else if(((S - 1023) & M) == (S - 1023)) System.out.println("Thanks");
		else System.out.println("Impossible");
	}
}