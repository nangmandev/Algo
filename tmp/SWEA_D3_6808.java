/**

 @author 한규준
 @since 2023-08-10
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D3_6808.java
 @youtube
 @performance 25380KB, 1327ms
 @category next permutation
 @note
 
 np로 수열 구하고 비교 진행

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D3_6808 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int TC = 1; TC <= tc; TC++) {
			int[] arr = new int[18];
			int[] gyArr = new int[9];
			int[] iyArr = new int[9];
			
			int win = 0;
			int lose = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0, j = 0; i < 9; i++) {
				int temp = Integer.parseInt(st.nextToken());
				arr[temp - 1] = 1;
				gyArr[j++] = temp;
			}
			
			for(int i = 0, j = 0; i < 18; i++) {
				if(arr[i] == 0) iyArr[j++] = i + 1;
			}
			
			Arrays.sort(iyArr);
			
			do {
				int iySum = 0;
				int gySum = 0;
				for(int i = 0; i < 9; i++) {
					if(gyArr[i] > iyArr[i]) {
						gySum += gyArr[i] + iyArr[i];
					}
					if(iyArr[i] > gyArr[i]) {
						iySum += gyArr[i] + iyArr[i];
					}
				}
				if(iySum > gySum) lose++;
				if(gySum > iySum) win++;
			}while(permu(iyArr));
			
			bw.write("#" + TC + " " + win + " " + lose + "\n");
		}
		
		bw.flush();
		bw.close();
		
	}
	
	private static boolean permu(int[] p) {
		int i = 8;
		while(i > 0 && p[i - 1] >= p[i]) {
			i--;
		}
		// peak찾기
		if(i == 0) return false;
		
		int j = 8;
		while(p[i - 1] >= p[j]) j--;
		
		swap(p, i - 1, j);
		
		int k = 8;
		while(i < k) {
			swap(p, i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int[] p, int a, int b) {
		int temp = p[a];
		p[a] = p[b];
		p[b] = temp;
	}
}
