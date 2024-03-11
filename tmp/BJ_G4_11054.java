/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/11054
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_11054.java
 @youtube
 @performance 12312KB, 108ms
 @category DP
 @note


 */

package algo;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class BJ_G4_11054 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] inc = new int[N];
		int[] dec = new int[N];
		
		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(arr[i] < arr[j]) inc[j] = Math.max(inc[i] + 1, inc[j]);
			}
		}
		
		for(int i = N - 1; i >= 0; i--) {
			for(int j = i - 1; j >= 0; j--) {
				if(arr[i] < arr[j]) dec[j] = Math.max(dec[i] + 1, dec[j]);
			}
		}
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			max = Math.max(max, inc[i] + dec[i]);
		}
		
		System.out.println(max + 1);
	}
}
