/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S3_11659.java
 @youtube
 @performance 77720KB, 760ms
 @category 누적합
 @note

 */
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_S3_11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] temp = br.readLine().split(" ");
		
		int N = Integer.parseInt(temp[0]), M = Integer.parseInt(temp[1]);
		String[] arr = br.readLine().split(" ");
		int[] realArr = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			realArr[i] = Integer.parseInt(arr[i - 1]) + realArr[i - 1];
		}
		
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			bw.write((realArr[Integer.parseInt(temp[1])] - realArr[Integer.parseInt(temp[0]) - 1]) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
