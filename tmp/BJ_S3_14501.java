/**

 @author 한규준
 @since 2023-09-05
 @see https://www.acmicpc.net/problem/14501
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_14501.java
 @youtube
 @performance 11556KB, 76ms
 @category DP
 @note

필기구 필참
 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S3_14501 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N + 1][3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0;
		for(int i = 0; i <= N; i++) {
			int next = i + arr[i][0];
			if(next <= N) {
				arr[next][2] = Math.max(Math.max(max + arr[i][1], arr[next][2]), arr[i][2] + arr[i][1]);
			}
			max = Math.max(max, arr[i][2]);
		}
		
		System.out.println(max);
	}
}
