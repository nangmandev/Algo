package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G3_16236 {
	static int N;
	static int[][] arr;
	static int[][] visited;
	static int count = 0;
	static PriorityQueue<Fish> priorityQueue;
	static BabyShark babyShark;
	
	public static class BabyShark{
		int level;
		int exp;
		int y;
		int x;
		public BabyShark(int level, int exp, int y, int x) {
			this.level = level;
			this.exp = exp;
			this.y = y;
			this.x = x;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public int getExp() {
			return exp;
		}
		public void setExp() {
			this.exp++;
			if(exp == level) {
				exp = 0;
				setLevel(getLevel() + 1);
			}
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
	}
	
	public static class Fish implements Comparable{
		int distance;
		int y;
		int x;
		public Fish(int distance, int y, int x) {
			this.distance = distance;
			this.y = y;
			this.x = x;
		}
		@Override
		public int compareTo(Object o) {
			Fish temp = (Fish) o;
			
			if(temp.distance == this.distance) {
				if(temp.y == this.y) {
					return Integer.compare(this.x, temp.x);
				} else {
					return Integer.compare(this.y, temp.y);
				}
			} else {
				return Integer.compare(this.distance, temp.distance);
			}
		}
	}
	
	public static class Yx{
		int y;
		int x;
		public Yx(int y, int x) {
			this.y = y;
			this.x = x;
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
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new int[N][N];
		int y = 0, x = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9) {
					y = i;
					x = j;
					arr[i][j] = 0;
				}
			}
		}
		
		priorityQueue = new PriorityQueue<>();
		babyShark = new BabyShark(2, 0, y, x);
		
		while(true) {
			int flag = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] != 0) flag = 1;
				}
			}
			if(flag == 0) break;
			
			BFS(babyShark.getY(), babyShark.getX());
			Fish temp;
			if(!priorityQueue.isEmpty()) temp = priorityQueue.poll();
			else break;
			count += temp.distance;
			babyShark.setX(temp.x);
			babyShark.setY(temp.y);
			
			arr[temp.y][temp.x] = 0;
			visited = new int[N][N];
			
			babyShark.setExp();
			priorityQueue.clear();
		}
		
		System.out.println(count);
	}
	
	public static void BFS(int y, int x) {
		Deque<Yx> deque = new ArrayDeque<>();
		deque.add(new Yx(y, x));
		visited[y][x] = 0;
		
		int[] movY = {-1, 0, 0, 1};
		int[] movX = {0, -1, 1, 0};
		while(!deque.isEmpty()) {
			Yx yx = deque.poll();
			
			int flag = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] < babyShark.getLevel()
					&& arr[i][j] != 0) {
						flag = 1;
					}
				}
			}
			if(flag == 0) break;
			
			if(arr[yx.getY()][yx.getX()] < babyShark.getLevel()
			&& arr[yx.getY()][yx.getX()] != 0) {
				// arr[yx.getY()][yx.getX()] = 0;
				// 물고기찾음
				// 뻗어나간 방향별로 물고기를 찾은 것이므로
				// 우선순위큐에 집어넣음
				priorityQueue.add(new Fish(visited[yx.getY()][yx.getX()], yx.getY(), yx.getX()));
			}
			
			for(int i = 0; i < 4; i++) {
				int nextY = yx.getY() + movY[i];
				int nextX = yx.getX() + movX[i];
				
				if(nextY >= 0 && nextY < N
				&& nextX >= 0 && nextX < N
				&& arr[nextY][nextX] <= babyShark.getLevel()
				&& visited[nextY][nextX] == 0) {
					deque.add(new Yx(nextY, nextX));
					visited[nextY][nextX] = visited[yx.getY()][yx.getX()] + 1;
				}
			}
		}
	}
}
