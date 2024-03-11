/**

 @author 한규준
 @since 2023-09-07
 @see https://www.acmicpc.net/problem/1699
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_1699.java
 @youtube
 @performance 12292KB, 120ms
 @category DP
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S2_1699 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N + 1];
		arr[1] = 1;
		
		for(int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] + 1;
			int temp = (int)Math.sqrt(i);
			
			if(temp * temp == i) {
				arr[i] = 1;
			}
			else {
				for(int j = 1; j <= temp; j++) {
					arr[i] = Math.min(arr[i], arr[i - (j * j)] + 1);
				}
			}
		}
		
		System.out.println(arr[N]);
	}
}
