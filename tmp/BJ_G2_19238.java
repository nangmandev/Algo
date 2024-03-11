/**

 @author 한규준
 @since 2023-10-05
 @see https://www.acmicpc.net/problem/19238
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_19238.java
 @youtube
 @performance 26092KB, 308ms	
 @category BFS, 구현
 @note

설계 맨처음에 잘하기

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G2_19238 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][N + 1];
		int[][] Pmap = new int[M][2];
		boolean[][] visited = new boolean[N + 1][N + 1];
		boolean[] Pvisited = new boolean[M];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) map[i][j] = -1;
				else if(map[i][j] == 1) map[i][j] = Integer.MIN_VALUE;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int[] start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), F, 0};
		int count = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int PsY = Integer.parseInt(st.nextToken());
			int PsX = Integer.parseInt(st.nextToken());
			int PdY = Integer.parseInt(st.nextToken());
			int PdX = Integer.parseInt(st.nextToken());
			map[PsY][PsX] = i;
			Pmap[i][0] = PdY;
			Pmap[i][1] = PdX;
		}
		
		Deque<int[]> deq = new ArrayDeque<int[]>();
		deq.offer(start);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1[0] != o2[0]) {
				return Integer.compare(o1[0], o2[0]);
			}
			else if(o1[1] != o2[1]) {
				return Integer.compare(o1[1], o2[1]);
			}
			else {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		int[] movY = {-1, 1, 0, 0};
		int[] movX = {0, 0, -1, 1};
		
		int result = Integer.MAX_VALUE;
		while(count < M) {
			// map bfs로 손님 탐색
			while(!deq.isEmpty()) {
				int[] nowYX = deq.poll();
				if(nowYX[2] == 0) continue;
				if(map[nowYX[0]][nowYX[1]] > -1) {
					if(!Pvisited[map[nowYX[0]][nowYX[1]]]) {
						// 거리, y, x, 연료
						pq.offer(new int[] {nowYX[3], nowYX[0], nowYX[1], nowYX[2], map[nowYX[0]][nowYX[1]]});
						continue;
					}
				}
				
				for(int i = 0; i < 4; i++) {
					int nextY = nowYX[0] + movY[i];
					int nextX = nowYX[1] + movX[i];
					if(nextY >= 1 && nextY <= N
					&& nextX >= 1 && nextX <= N
					&& !visited[nextY][nextX]
					&& map[nextY][nextX] != Integer.MIN_VALUE) {
						visited[nextY][nextX] = true;
						deq.offer(new int[] {nextY, nextX, nowYX[2] - 1, nowYX[3] + 1});
					}
				}
			}
			
			// pq가 0이면 손님 못찾고 연료 끝난것
			if(pq.isEmpty()) {
				result = -1;
				break;
			}
			
			int[] Pd = pq.poll();
			int dest = Pd[4];
			int fuel = Pd[3];
			Pvisited[dest] = true;
			
			pq.clear();
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					visited[i][j] = false;
				}
			}
			
			int prevCount = count;
			deq.offer(new int[] {Pd[1], Pd[2], Pd[3]});
			while(!deq.isEmpty()) {
				int[] nowYX = deq.poll();
				if(nowYX[0] == Pmap[dest][0] && nowYX[1] == Pmap[dest][1]) {
					if(count == M - 1) {
						result = nowYX[2] + (fuel - nowYX[2]) * 2;
						break;
					}
					count++;
					deq.clear();
					deq.offer(new int[] {nowYX[0], nowYX[1], nowYX[2] + (fuel - nowYX[2]) * 2, 0});
					
					for(int i = 1; i <= N; i++) {
						for(int j = 1; j <= N; j++) {
							visited[i][j] = false;
						}
					}
					
					break;
				}
				if(nowYX[2] == 0) continue;
				
				for(int i = 0; i < 4; i++) {
					int nextY = nowYX[0] + movY[i];
					int nextX = nowYX[1] + movX[i];
					if(nextY >= 1 && nextY <= N
					&& nextX >= 1 && nextX <= N
					&& !visited[nextY][nextX]
					&& map[nextY][nextX] != Integer.MIN_VALUE) {
						visited[nextY][nextX] = true;
						deq.offer(new int[] {nextY, nextX, nowYX[2] - 1});
					}
				}
 			}
			
			if(count == prevCount && result == Integer.MAX_VALUE) {
				result = -1;
				break;
			} else if(result != Integer.MAX_VALUE) {
				break;
			}
		}
		
		System.out.println(result);
	}
}
