/**

@author 한규준
@since 2023-08-01
@see
@git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BOJ15649.java
@youtube
@performance 75712KB, 2148ms
@category BruteForce, BackTracking, Permutation
@note

*/

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ_S3_15649 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N개 중 중복 없이 M개 -> 완탐 
		
		String[] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]), M = Integer.parseInt(str[1]);
		int[] arr = new int[N];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		
		makePermutation(0, new int[M], new boolean[N], arr);
		
	}
	
	public static void makePermutation(int nthChoice, int[] choosed, boolean[] visited, int[] arr) {
		if(nthChoice == choosed.length) {
			for(int i = 0; i < choosed.length; i++) {
				System.out.print((choosed[i] + 1) + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = arr[i];
				makePermutation(nthChoice + 1, choosed, visited, arr);
				visited[i] = false;
			}
		}
	}
}
