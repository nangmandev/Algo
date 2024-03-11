/**

 @author 한규준
 @since 2023-08-11
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1753.java
 @youtube
 @performance 121004KB, 824ms
 @category 다익스트라
 @note

다익스트라 구현

 */


package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1753 {
	static int V, E, start;
	static ArrayList<ArrayList<Road>> edges = new ArrayList<>();
	static int[] distance;
	
	static final int INF = 987654321;
	
	static class Road{
		int next;
		int weight;
		public Road(int next, int weight) {
			this.next = next;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		distance = new int[V + 1];
		
		for(int i = 0; i <= V; i++) {
			edges.add(new ArrayList<>());
			distance[i] = INF;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges.get(u).add(new Road(v, w));
		}
		
		// 가장 가중치가 작은 노드로 가서
		// 모든 간선 탐색
		
		PriorityQueue<Road> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
		priorityQueue.add(new Road(start, 0));
		distance[start] = 0;
		
		while(!priorityQueue.isEmpty()) {
			Road nowRoad = priorityQueue.poll();
			if(nowRoad.weight > distance[nowRoad.next]) {
				continue;
			}
			
			for(int i = 0; i < edges.get(nowRoad.next).size(); i++) {
				Road nextRoad = edges.get(nowRoad.next).get(i);
				if(distance[nextRoad.next] > nowRoad.weight + nextRoad.weight) {
					distance[nextRoad.next] = nowRoad.weight + nextRoad.weight;
					priorityQueue.add(new Road(nextRoad.next, distance[nextRoad.next]));
				}
			}
		}
		
		for(int i = 1; i <= V; i++) {
			if(distance[i] == INF) bw.write("INF" + "\n");
			else bw.write(distance[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
