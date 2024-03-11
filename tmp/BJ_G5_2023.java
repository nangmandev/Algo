/**

 @author 한규준
 @since 2023-08-03
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_2023.java
 @youtube
 @performance 12576KB, 4668ms
 @category 백트래킹, 소수, 경우의수 쪼개기
 @note

 천의 자리에서 먼저 판별을 해서 넘기고(1000단위로)
 백의 자리에서...
 를 반복하면 반복횟수/메모리 소모를 줄일 수 있습니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_G5_2023 {
	static int tempNumber;
	static int tempN;
	static int flag;
	static int sqrtNumber;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int maxNumber = (int)Math.pow(10, N);
		
		for(int i = maxNumber / 10; i < maxNumber; i++) {
			if(i > 10 && (i % 2 == 0 || i % 3 == 0)) continue;
			tempN = N;
			flag = 0;
			
			while(tempN > 0) {
				tempNumber = i / (int)Math.pow(10, tempN-- - 1);
				sqrtNumber = (int)(Math.sqrt(tempNumber));
			    for(int k = 2; k <= sqrtNumber; k++) {
				    if(tempNumber % k == 0) {
					    flag = 1;
					    break;
				    }
			    }
			    if(flag == 1 || tempNumber == 1) {
			    	flag = 1;
					break;
				}
			}
			
			if(flag == 1) {
				flag = 0;
				continue;
			}else bw.write(i + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
