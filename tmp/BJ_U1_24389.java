/**

 @author 한규준
 @since 2023-09-01
 @see https://www.acmicpc.net/problem/24389
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U1_24389.java
 @youtube
 @performance 11492KB, 76ms
 @category 비트계산
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_U1_24389 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int max_Number = Integer.MAX_VALUE;
		char[] o = new char[32];
		String t = Integer.toBinaryString(N);
		
		int idx = o.length - 1;
		for(int i = t.length() - 1; i >= 0; i--) {
			o[idx--] = t.charAt(i);
		}
		
		N = ~N + 1;
		
		char[] r = Integer.toBinaryString(N).toCharArray();
		
		int count = 0;
		for(int i = 0; i < 32; i++) {
			if(r[i] != o[i]) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}