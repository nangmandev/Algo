/**

 @author 한규준
 @since 2023-08-11
 @see https://www.acmicpc.net/problem/2206
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_2206.java
 @youtube
 @performance 206476KB, 852ms
 @category BFS
 @note

 1. 그냥 BFS로 풀기 -> 안됨
 2. 1 있는곳 구해서 BFS다돌기 -> 안됨(시간복잡도)
 3. Yx class에 status(벽부수는 스킬 썼는지) 체크 -> 안됨(부순노드/안부순노드 충돌)
 4. visited배열 2개 생성, status별 체크, 스킬 사용하면 visited넘어가기 -> 성공

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G3_2206 {
	static int N, M;
	static int[][] arr;
	static int[][][] visited;
	static int minValue = Integer.MAX_VALUE;
	
	public static class Yx{
		int y, x;
		boolean skillUsed;
		public Yx(int y, int x, boolean tf) {
			this.y = y;
			this.x = x;
			this.skillUsed = tf;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public boolean isSkillUsed() {
			return skillUsed;
		}
		public void setSkillUsed(boolean skillUsed) {
			this.skillUsed = skillUsed;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		// 벽 하나도 안 부순거 하고나서
		// 벽 부순거 BFS ㄱ
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j) - '0';
			}
		}
		// 스킬 안쓴경우 1번째
		// 스킬 쓴경우 2번째 visited를 사용한다.
		visited = new int[N][M][2];
		
		Deque<Yx> deq = new ArrayDeque<>();
		deq.add(new Yx(0, 0, false));
		visited[0][0][0] = 1;
		visited[0][0][1] = 1;
		
		while(!deq.isEmpty()) {
			Yx nowYx = deq.poll();
			int skill = 0;
			// 스킬 쓴경우 true, 1번 visited사용
			if(nowYx.isSkillUsed()){
				skill = 1;
				// 안쓴경우 false, 0번 visited사용
			} else skill = 0;

			if(nowYx.y == N - 1 && nowYx.x == M - 1){
				if(visited[nowYx.y][nowYx.x][skill] < minValue){
					minValue = visited[nowYx.y][nowYx.x][skill];
				}
				continue;
			}

			int[] movX = {1, 0, -1, 0};
			int[] movY = {0, 1, 0, -1};
			for(int i = 0; i < 4; i++){
				int nextY = nowYx.getY() + movY[i];
				int nextX = nowYx.getX() + movX[i];

				if(nextY >= 0 && nextY < N
				&& nextX >= 0 && nextX < M
				&& visited[nextY][nextX][skill] == 0){
					// 스킬 안썼고 안쓰는경우, 스킬 썼었고 0으로 간경우, 스킬 이제 쓴경우
					// 스킬 안썼고 안쓰는경우
					if(skill == 0 && arr[nextY][nextX] == 0){
						visited[nextY][nextX][skill] = visited[nowYx.y][nowYx.x][skill] + 1;
						deq.add(new Yx(nextY, nextX, false));
					}
					// 스킬 썼었고 0으로 간경우
					else if(skill == 1 && arr[nextY][nextX] == 0){
						visited[nextY][nextX][skill] = visited[nowYx.y][nowYx.x][skill] + 1;
						deq.add(new Yx(nextY, nextX, true));
					}
					// 스킬 이제 쓸경우. 스킬 쓴 visited도 확인해야함
					else if(skill == 0 && arr[nextY][nextX] == 1 && visited[nextY][nextX][1] == 0){
						visited[nextY][nextX][1] = visited[nowYx.y][nowYx.x][skill] + 1;
						deq.add(new Yx(nextY, nextX, true));
					}
				}
			}
		}
		
		if(minValue == Integer.MAX_VALUE) bw.write(-1 + "\n");
		else bw.write(minValue + "\n");

		bw.flush();
		bw.close();
	}
}