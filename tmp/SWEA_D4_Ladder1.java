/**

@author 한규준
@since 2023-08-01
@see
@git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_Ladder1.java
@youtube
@performance 32420kb, 156ms
@category BruteForce
@note

*/
package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_D4_Ladder1 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 10;
		
		for(int i = 1; i <= tc; i++) {
			int tc1 = Integer.parseInt(br.readLine());
			int[][] arr = new int[100][100];
			// 사다리 맵 초기화
			for(int j = 0; j < 100; j++) {
				String[] str = br.readLine().split(" ");
				for(int k = 0; k < 100; k++) {
					arr[j][k] = Integer.parseInt(str[k]);
				}
			}
			
			// 시작지점찾기
			// 아래에서 위로(도착 -> 시작)으로 가면 한번만 탐색하면 된다.
			int X = 0, Y = 99;
			for(int j = 0; j < 100; j++) {
				if(arr[99][j] == 2) {
					X = j;
				}
			}
			
			// 좌우 무한루프가 걸릴 수 있으므로, 한 방향으로만 가게 한다.
			int lastlr = 0;
			while(Y != 0) {
				// 좌, 우, 위로 3방향 검사 및 이동
				if(X > 0 && arr[Y][X - 1] == 1 && lastlr != 1) {
					X--;
					lastlr = -1;
				} else if(X < 99 && arr[Y][X + 1] == 1 && lastlr != -1) {
					X++;
					lastlr = 1;
				} else if(arr[Y - 1][X] == 1) {
					Y--;
					lastlr = 0;
				}
			}
			
			// Y가 0이 되면 출발지점이므로 출력
			System.out.printf("#%d %d\n", i, X);
		}
	}
}
