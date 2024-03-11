/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/2294
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_2294.java
 @youtube
 @performance 12160KB, 100ms
 @category DP
 @note

동전 가치만큼 배열을 이동하며 탐색합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G5_2294 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N];
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int[] value = new int[K + 1];
		value[0] = 0;
		
		for(int i = 1; i <= K; i++) {
			value[i] = 1000000000;
			for(int j = 0; j < N; j++) {
				if(i >= coins[j]) {
					value[i] = Math.min(value[i - coins[j]] + 1, value[i]);
				}
			}
		}
		
		if(value[K] == 1000000000) System.out.println(-1);
		else System.out.println(value[K]);
	}
}
