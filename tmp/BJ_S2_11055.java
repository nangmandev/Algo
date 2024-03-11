/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/11055
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11055.java
 @youtube
 @performance 12348KB, 104ms
 @category DP
 @note


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_11055 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[][] res = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			res[i][0] = arr[i];
			res[i][1] = arr[i];
		}
		
		int max = 0;
		res[0][0] = arr[0];
		res[0][1] = arr[0];
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(arr[j] > arr[i]) {
					res[j][0] = arr[i];
					res[j][1] = Math.max(res[i][1] + arr[j], res[j][1]);
				}
			}
			max = Math.max(max, res[i][1]);
		}
		
		System.out.println(max);
	}
}