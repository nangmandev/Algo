/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/11052
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_11052.java
 @youtube
 @performance 11960KB, 96ms
 @category DP
 @note

조합으로 구해야함

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S1_11052 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N + 1];
		int[] res = new int[N + 1];
		int max = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			res[i] = P[i];
			for(int j = 1; j <= i / 2; j++) {
				res[i] = Math.max(res[i - j] + res[j], res[i]);
			}
		}
		
		System.out.println(res[N]);
	}
}
