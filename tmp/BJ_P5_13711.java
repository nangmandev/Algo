/**

 @author 한규준
 @since 2023-09-13
 @see https://www.acmicpc.net/problem/13711
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_P5_13711.java
 @youtube
 @performance 47276KB, 460ms
 @category 수열, DP
 @note

특정 조건에서의 증가하는 공통 부분수열

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_P5_13711 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N + 1];
		int[] B = new int[N + 1];
		int[] dp = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 1; i <= N; i++){
			map.put(Integer.parseInt(st.nextToken()), i);
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++){
			B[i] = map.get(Integer.parseInt(st.nextToken()));
		}
		
		int dpIdx = 2;
		dp[1] = B[1];
		for(int i = 2; i <= N; i++) {
			if(dp[dpIdx - 1] < B[i]) {
				dp[dpIdx++] = B[i];
			}
			else {
				int left = 1;
				int right = dpIdx - 1;
				while(left < right) {
					int mid = (left + right) / 2;
					if(dp[mid] < B[i]) {
						left = mid + 1;
					}
					else right = mid;
				}
				dp[left] = B[i];
			}
		}
		System.out.println(dpIdx - 1);
	}
}
