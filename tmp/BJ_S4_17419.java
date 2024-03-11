/**

 @author 한규준
 @since 2023-08-31
 @see https://www.acmicpc.net/problem/17419
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S4_17419.java
 @youtube
 @performance 19744KB, 168ms
 @category 비트마스킹
 @note
 
 1 개수

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_S4_17419 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String temp = br.readLine();
		
		int count = 0;
		for(int i = 0; i < n; i++) {
			if(temp.charAt(i) == '1') count++;
		}
		
		System.out.println(count);
	}
}
