/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/11053
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11053.java
 @youtube
 @performance 11924KB, 104ms
 @category DP
 @note

DP를 쭉 돌면서
자기보다 큰 값 있고 길이가 더 길면
길이 더해주기

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_11053 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(arr[i][0] < arr[j][0]) arr[j][1] = Math.max(arr[j][1], arr[i][1] + 1);
				result = Math.max(result, arr[j][1]);
			}
		}
		
		System.out.println(result + 1);
	}
}