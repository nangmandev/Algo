/**

 @author 한규준
 @since 2023-09-13
 @see https://www.acmicpc.net/problem/19539
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_19539.java
 @youtube
 @performance 22084KB, 300ms
 @category 구현
 @note


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_19539 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int One = 0;
		int Two = 0;
		
		for(int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			
			if(temp % 2 == 1) {
				temp -= 1;
				One++;
			}
			
			Two += temp / 2;
		}
		
		while(Two > One) {
			Two--;
			One += 2;
		}
		
		if(Two == One) System.out.println("YES");
		else System.out.println("NO");
	}
}
