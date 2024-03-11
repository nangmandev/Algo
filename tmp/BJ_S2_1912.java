/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/1912
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_1912.java
 @youtube
 @performance 24032KB, 196ms
 @category DP
 @note

연속합이므로, 현재까지의 합(이전값)과 자기자신 중 큰 것을 선택해 기록

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_1912 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken());
		}
		
		arr[0][1] = arr[0][0];
		int result = arr[0][0];
		for(int i = 1; i < N; i++) {
			arr[i][1] = Math.max(arr[i - 1][1] + arr[i][0], arr[i][0]);
			result = Math.max(arr[i][1], result);
		}
		
		System.out.println(result);
	}
}
