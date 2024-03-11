/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_1861.java
 @youtube
 @performance
 @category BFS
 @note

NxN행렬에서, 상하좌우로 이동가능
이때, 다음 방이 현재 방 + 1 의 값을 가지고 있어야 이동가능
최대로 이동 가능한 방을 구하기.
최대 이동 가능 방 개수가 중복이면 가장 적은 숫자 출력

1. BFS로 모든 방 탐색


 */
package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA_D4_1861 {
	
	private static class Room {
		int y;
		int x;
		int number;
		
		public Room(int y, int x, int number) {
			this.y = y;
			this.x = x;
			this.number = number;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int tc = Integer.parseInt(br.readLine());
		for(int TC = 1; TC <= tc; TC++) {
			int maxValue = 0;
			int roomNumber = Integer.MAX_VALUE;
			Deque<Room> deque = new ArrayDeque<>();
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			int[][] visited;// = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[] movX = new int[] {1, -1, 0, 0};
			int[] movY = new int[] {0, 0, 1, -1};
			// 초기화 끝
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visited = new int[N][N];
					deque.add(new Room(i, j, arr[i][j]));
					int tempSum = 0;
					
					while(!deque.isEmpty()) {
						Room nowRoom = deque.poll();
						visited[nowRoom.y][nowRoom.x] = 1;
						tempSum++;
						
						for(int k = 0; k < 4; k++) {
							int nextY = nowRoom.y + movY[k];
							int nextX = nowRoom.x + movX[k];
							if(nextY >= 0 && nextY < N
							&& nextX >= 0 && nextX < N
							&& visited[nextY][nextX] == 0
							&& arr[nextY][nextX] == arr[nowRoom.y][nowRoom.x] + 1) {
								deque.add(new Room(nextY, nextX, arr[nextY][nextX]));
							}
						}
					}
					
					if(tempSum > maxValue) {
						maxValue = tempSum;
						roomNumber = arr[i][j];
					}
					if(arr[i][j] < roomNumber && tempSum == maxValue) roomNumber = arr[i][j];
					deque.clear();
				}
			}
			bw.write("#" + TC + " " + roomNumber + " " + maxValue + "\n");
		}
		bw.flush();
		bw.close();
	}
}
