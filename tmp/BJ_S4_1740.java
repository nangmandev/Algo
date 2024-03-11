/**

 @author 한규준
 @since 2023-09-01
 @see https://www.acmicpc.net/problem/1740
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_1740.java
 @youtube
 @performance 11456KB, 76ms
 @category 비트마스킹, 문제읽기
 @note
 
 문제읽기

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S4_1740 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long N = Long.parseLong(br.readLine());
		long result = 0;
		
		String st = Long.toBinaryString(N);
		
		for(int i = 0; i < st.length(); i++) {
			if(st.charAt(i) == '1') {
				result += asb(3, (st.length() - i - 1));
			}
		}
		
		System.out.println(result);
	}
	
	private static long asb(long val, long exp) {
		long rtn = 1;
		for(int i = 0; i < exp; i++) {
			rtn *= val;
		}
		return rtn;
	}
}
