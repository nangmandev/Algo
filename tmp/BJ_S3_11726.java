/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/11726
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_11726.java
 @youtube
 @performance 11600KB, 80ms
 @category DP
 @note

벽돌 크기에 따른 조합 개수를 조금 세고 점화식
-> 피보나치

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S3_11726 {
	static int[] arr = new int[1001];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		arr[1] = 1;
		arr[2] = 2;
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 3; i <= n; i++) {
			arr[i] = (arr[i - 1] + arr[i - 2]) % 10007;
		}
		
		bw.write(arr[n] + "\n");
		bw.flush();
		bw.close();
	}
}
