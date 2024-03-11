/**

 @author 한규준
 @since 2023-08-31
 @see https://www.acmicpc.net/problem/1094
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S5_1094.java
 @youtube
 @performance 11520KB, 75ms
 @category 비트마스킹
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S5_1094 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int X = Integer.parseInt(br.readLine());
		
		int count = 0;
		for(int i = 64; i > 0; i /= 2) {
			if((i & X) != 0) count++;
		}
		
		System.out.println(count);
	}
}
