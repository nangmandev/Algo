/**

@author 한규준
@since 2023-08-01
@see
@git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D3_2805.java
@youtube
@performance 22140kb, 132ms
@category BruteForce
@note

*/
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_D3_2805 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			
			// 배열 초기화
			for(int j = 0; j < N; j++) {
				String str = br.readLine();
				for(int k = 0; k < N; k++) {
					arr[j][k] = str.charAt(k) - '0';
				}
			}
			
			// 마름모 탐색하는 방식
			// 1. 가운데 가장 긴 행을 탐색한다.
			// 2. 위아래로 시작지점이 1 증가, 종료지점이 1 감소하므로 시작/종료지점 초기설정을 한다.
			int startX = 0, endX = N - 1;
			int sum = 0;
			for(int j = 0; j < N; j++) {
				sum += arr[N / 2][j];
			}
			
			// 3. y좌표를 위아래로 한칸씩 증가시키고 시작지점/종료지점을 1씩 증가/감소시킨다.
			// 이때, y좌표의 변동량은 Math.floor(N/2)인데, int형이므로 소수점이 버려져 상관없다.
			
			for(int j = 1; j <= N / 2; j++) {
				startX++;
				endX--;
				for(int k = startX; k <= endX; k++) {
					sum += arr[N / 2 + j][k];
					sum += arr[N / 2 - j][k];
				}
			}
			
			// 출력
			//System.out.printf("#%d %d\n", i, sum);
			bw.write("#" + i + " " + sum + "\n");
		}
		bw.flush();
		bw.close();
	}
}
