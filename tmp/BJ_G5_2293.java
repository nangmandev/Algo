/**

 @author 한규준
 @since 2023-09-08
 @see https://www.acmicpc.net/problem/2293
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_2293.java/
 @youtube
 @performance 16208KB, 204ms
 @category DP
 @note



 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G5_2293 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[n];
		int[][] res = new int[n][k + 1];
		
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
			if(coin[i] <= k) {
				res[i][coin[i]] = 1;
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = coin[i] + 1; j <= k; j++) {
				for(int l = 0; l <= i; l++) {
					res[i][j] += res[l][j - coin[i]];
				}
				if(res[i][j] == 0 && j % coin[i] == 0) {
					res[i][j] = 1;
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i < n; i++) {
			max += res[i][k];
		}
		
		System.out.println(max);
	}
}
