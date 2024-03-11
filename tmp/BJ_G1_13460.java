/**

 @author 한규준
 @since 2023-09-14
 @see https://www.acmicpc.net/problem/13460
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G1_13460.java
 @youtube
 @performance 11728KB, 76ms
 @category 구현
 @note

visited유의

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G1_13460 {
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	
	static int[] movY = {-1, 1, 0, 0};
	static int[] movX = {0, 0, -1, 1};
	
	static class mvBall{
		int y;
		int x;
		int count;
		int move;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][N][M];
		
		mvBall redBall = new mvBall();
		mvBall blueBall = new mvBall();
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == 'B') {
					blueBall.x = j;
					blueBall.y = i;
					blueBall.count = 0;
					map[i][j] = '.';
				}
				else if(map[i][j] == 'R') {
					redBall.x = j;
					redBall.y = i;
					redBall.count = 0;
					map[i][j] = '.';
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		
		Deque<mvBall[]> deq = new ArrayDeque<>();
		mvBall[] list = new mvBall[] {redBall, blueBall};
		visited[redBall.y][redBall.x][blueBall.y][blueBall.x] = true;
		deq.offer(list);
		
		while(!deq.isEmpty()) {
			mvBall[] nowList = deq.poll();
			// 0 : red, 1 : blue
			
			for(int i = 0; i < 4; i++) {
				mvBall nextRed = mov(i, nowList[0]);
				mvBall nextBlue = mov(i, nowList[1]);
				
				// 파란공이 들어갔으면 패스
				if(nextBlue.y == -1) {
					continue;
				}
				// 빨간공만 들어갔으면 기록
				else if(nextRed.y == -1 && nextRed.count <= 10) {
					result = Math.min(result, nextRed.count);
					continue;
				}
				
				// 빨간공 움직인 횟수가 10넘어가면 패스
				if(nextRed.count > 10) {
					continue;
				}
				
				// 같은 곳에 있는경우
				if(nextRed.x == nextBlue.x && nextRed.y == nextBlue.y) {
					//더 많이 움직인 공의 위치 한칸 되돌리기
					if(nextRed.move > nextBlue.move) {
						nextRed.x -= movX[i];
						nextRed.y -= movY[i];
					}
					else if(nextBlue.move > nextRed.move) {
						nextBlue.x -= movX[i];
						nextBlue.y -= movY[i];
					}
				}
				
				// 방문처리
				if(visited[nextRed.y][nextRed.x][nextBlue.y][nextBlue.x]) {
					continue;
				}
				else {
					visited[nextRed.y][nextRed.x][nextBlue.y][nextBlue.x] = true;
					mvBall[] newList = new mvBall[] {nextRed, nextBlue}; 
					deq.offer(newList);
				}
				
			}
		}
		
		if(result == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(result);
	}
	
	private static mvBall mov(int direction, mvBall ball){
		mvBall newBall = new mvBall();
		newBall.y = ball.y;
		newBall.x = ball.x;
		newBall.count = ball.count;
		newBall.move = 0;
		while(true) {
			if(map[newBall.y][newBall.x] == 'O') {
				newBall.y = -1;
				newBall.x = -1;
				newBall.move++;
				break;
			}
			int nextY = newBall.y + movY[direction];
			int nextX = newBall.x + movX[direction];
			if(nextY >= 0 && nextY < N
			&& nextX >= 0 && nextX < M
			&& (map[nextY][nextX] == '.' || map[nextY][nextX] == 'O')) {
				newBall.y = nextY;
				newBall.x = nextX;
				newBall.move++;
			}
			else break;
		}
		newBall.count++;
		return newBall;
	}
}
