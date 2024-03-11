/**

 @author 한규준
 @since 2023-08-24
 @see https://www.acmicpc.net/problem/19236
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_19236.java
 @youtube
 @performance 11864KB, 80ms
 @category 구현, 시뮬레이션, DFS
 @note

오타체크, 논리체크 잘할것
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G2_19236 {
	static int result = 0;
	
	static int[] movY = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] movX = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int[][] fishes = new int[17][2];
		int[][][] map = new int[5][5][2];
		
		for(int i = 1; i <= 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				map[i][j][0] = num;
				map[i][j][1] = direction;
				fishes[num][0] = i;
				fishes[num][1] = j;
			}
		}
		
		DFS(1, 1, 0, fishes, map, 0);
		
		System.out.println(result);
	}
	
	public static void DFS(int y, int x, int direction, int[][] fishes, int[][][] map, int eat) {
		int tempEat = eat + map[y][x][0];
		int tempD = map[y][x][1];
		//System.out.println(y + " " + x + " " + map[y][x][0] + " " + tempD);
		fishes[map[y][x][0]][0] = 0;
		map[y][x][0] = 0;
		map[y][x][1] = 0;
		result = Math.max(tempEat, result);
		
		// 물고기 이동이 여기 들어가야 함
		fishMove(y, x, fishes, map);
		
		
		int nextY = y + movY[tempD];
		int nextX = x + movX[tempD];
		while(true) {
			if(nextY >= 1 && nextY <= 4
			&& nextX >= 1 && nextX <= 4) {
				if(map[nextY][nextX][0] != 0) {
					int[][][] newArr = new int[5][5][2];
					int[][] newFish = new int[17][2];
					for(int i = 1; i <= 4; i++) {
						for(int j = 1; j <= 4; j++) {
							for(int k = 0; k <= 1; k++) {
								newArr[i][j][k] = map[i][j][k];
							}
						}
					}
					for(int i = 1; i <= 16; i++) {
						for(int j = 0; j < 2; j++) {
							newFish[i][j] = fishes[i][j];
						}
					}
					DFS(nextY, nextX, tempD, newFish, newArr, tempEat);
				}
			} else break;
			nextY += movY[tempD];
			nextX += movX[tempD];
		}
		
		
	}
	
	private static void fishMove(int sY, int sX, int[][] fishes, int[][][] map) {
		for(int i = 1; i <= 16; i++) {
			if(fishes[i][0] == 0) continue;
			int nowY = fishes[i][0];
			int nowX = fishes[i][1];
			
			int nextY = nowY + movY[map[nowY][nowX][1]];
			int nextX = nowX + movX[map[nowY][nowX][1]];
			
			while(true) {
				if(nextY <= 0 || nextY > 4
				|| nextX <= 0 || nextX > 4
				|| (nextY == sY && nextX == sX)) {
					map[nowY][nowX][1] = map[nowY][nowX][1] % 8 + 1;
					nextY = nowY + movY[map[nowY][nowX][1]];
				    nextX = nowX + movX[map[nowY][nowX][1]];
				}
				else break;
			}
			
			// 이제 이동할 수 있다
			// 자리가 비어있으면 그냥 가면 된다.
			if(map[nextY][nextX][0] == 0) {
				map[nextY][nextX][0] = i;
				map[nextY][nextX][1] = map[nowY][nowX][1];
				fishes[i][0] = nextY;
				fishes[i][1] = nextX;
				map[nowY][nowX][0] = 0;
				map[nowY][nowX][1] = 0;
			}
			// 자리가 비어있지 않으면 교체해야 한다.
			else {
				int tempIndex = map[nextY][nextX][0];
				int tempD = map[nextY][nextX][1];
				map[nextY][nextX][0] = map[nowY][nowX][0];
				map[nextY][nextX][1] = map[nowY][nowX][1];
				map[nowY][nowX][0] = tempIndex;
				map[nowY][nowX][1] = tempD;
				fishes[i][0] = nextY;
				fishes[i][1] = nextX;
				fishes[tempIndex][0] = nowY;
				fishes[tempIndex][1] = nowX;
			}
		}
	}
}