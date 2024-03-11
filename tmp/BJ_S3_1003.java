/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/1003
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_1003.java
 @youtube
 @performance 11556KB, 72ms
 @category DP
 @note

재귀호출하는 순간 시간초과(0.25초)
배열을 만들어서 피보나치수 저장
n - 1, n번째 수 출력

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S3_1003 {
	static int[] arr = new int[41];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		arr[0] = 0;
		arr[1] = 1;
		
		for(int i = 2; i < 41; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
			
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			
			int n = Integer.parseInt(br.readLine());
			if(n == 0) bw.write(1 + " " + 0 + "\n");
			else bw.write(arr[n - 1] + " " + arr[n] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
