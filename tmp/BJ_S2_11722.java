/**

 @author 한규준
 @since 2023-09-07
 @see https://www.acmicpc.net/problem/11722
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11722.java
 @youtube
 @performance 12344KB, 160ms
 @category DP
 @note
 

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_11722 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = 1;
		}
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(arr[i][0] > arr[j][0]) {
					arr[j][1] = Math.max(arr[j][1], arr[i][1] + 1);
				}
			}
			max = Math.max(max, arr[i][1]);
		}
		
		System.out.println(max);
	}
}
