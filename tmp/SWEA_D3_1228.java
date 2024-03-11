/**

 @author 한규준
 @since 2023-08-08
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D3_1228.java
 @youtube
 @performance 18396KB, 118ms
 @category 문제읽기, 문자열, 배열, 입출력
 @note

크기 10 String배열 선언, 0~9 사이인 경우에만 삽입 진행, 10개만 출력

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_D3_1228 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			String[] res = new String[10];
			int N = Integer.parseInt(br.readLine());
			String[] origin = br.readLine().split(" ");
			for(int i = 0; i < 10; i++) {
				res[i] = origin[i];
			}
			
			int instN = Integer.parseInt(br.readLine());
			String[] plus = br.readLine().split(" ");
			String temp;
			
			int strIdx = 0;
			while(true) {
				if(strIdx > plus.length - 1) break;
				
				int x;
				int y;
				
				if(plus[strIdx++].equals("I")) {
					x = Integer.parseInt(plus[strIdx++]);
					y = Integer.parseInt(plus[strIdx++]);
					for(int j = x; j < x + y; j++) {
						if(j >= 0 && j < N) {
							for(int k = N - 1; k > j; k--) {
								origin[k] = origin[k - 1];
							}
							origin[j] = plus[strIdx++];
						}
						else strIdx++;
					}
				}
			}
			
			System.out.print("#" + tc + " ");
			for(int i = 0; i < 10; i++) {
				System.out.print(origin[i] + " ");
			}
			System.out.print("\n");
			
		}
	}
}
