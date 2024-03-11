/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/11048
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11048.java
 @youtube
 @performance 99252KB, 6116ms
 @category DP
 @note

왼쪽, 위, 대각선 왼쪽위를 체크하면서 갱신해나가면 됩니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_S2_11048 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][][] arr = new int[N][M][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		arr[0][0][1] = arr[0][0][0];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(i > 0) {
					arr[i][j][1] = Math.max(arr[i - 1][j][1] + arr[i][j][0], arr[i][j][1]);
				}
				if(j > 0) {
					arr[i][j][1] = Math.max(arr[i][j - 1][1] + arr[i][j][0], arr[i][j][1]);
				}
				if(i > 0 && j > 0) {
					arr[i][j][1] = Math.max(arr[i - 1][j - 1][1] + arr[i][j][0], arr[i][j][1]);
				}
			}
		}
		
		System.out.println(arr[N - 1][M - 1][1]);
	}
}
