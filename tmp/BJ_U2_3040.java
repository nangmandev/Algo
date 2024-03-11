/**

 @author 한규준
 @since 2023-08-11
 @see https://www.acmicpc.net/problem/3040
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_U2_3040.java
 @youtube
 @performance 11424KB, 76ms
 @category 조합
 @note

9개의 숫자 중
7개의 조합을 구하여(순서 상관 X)
7개 숫자의 합이 100이면 출력합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_U2_3040 {
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[9];
		
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		combi(0, new int[7], 0);
	}
	
	public static void combi(int nth, int[] tempArr, int start) {
		if(nth == tempArr.length) {
			int temp = 0;
			for(int i = 0; i < nth; i++) {
				temp += tempArr[i];
			}
			if(temp == 100) {
				for(int i = 0; i < nth; i++) {
					System.out.println(tempArr[i]);
				}
			}
			return;
		}
		
		for(int i = start; i < 9; i++) {
			tempArr[nth] = arr[i];
			combi(nth + 1, tempArr, i + 1);
		}
	}
}
