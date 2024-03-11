/**

 @author 한규준
 @since 2023-08-02
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_11660.java
 @youtube
 @performance 156236KB, 1320ms
 @category 구간합
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_S1_11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] temp = br.readLine().split(" ");
		
		int N = Integer.parseInt(temp[0]), M = Integer.parseInt(temp[1]);
		int[][] arr = new int[N][N + 1];
		int sum = 0, i = 0, j = 0;
		int[] receive = new int[4];
		
		for(i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			for(j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(temp[j - 1]) + arr[i][j - 1];
			}
		}
		
		for(i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			for(j = 0; j < 4; j++) {
				receive[j] = Integer.parseInt(temp[j]);
			}
			sum = 0;
			for(j = receive[0] - 1; j < receive[2]; j++) {
				sum += arr[j][receive[3]] - arr[j][receive[1] - 1];
			}
			sb.append(sum).append("\n");
		}
		
		System.out.print(sb);
	}
}
