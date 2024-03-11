package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_P5_2887 {
	public static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int cost;
		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	public static class Planet{
		int num;
		int x;
		int y;
		int z;
		public Planet(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	
	static int N, planet[];
	static Planet[] planets;
	static Edge[] edges;
	static int result = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		planet = new int[N];
		for(int i = 0; i < N; i++) {
			planet[i] = i;
		}
		planets = new Planet[N];
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		
		int x, y, z;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			planets[i] = new Planet(i, x, y, z);
		}
		
		Arrays.sort(planets, (o1, o2) -> Integer.compare(o1.x, o2.x));
		int idx = 0;
		for(int i = 0; i < N - 1; i++){
			int p1 = planets[i].num;
			int p2 = planets[i + 1].num;
			int cost = Math.abs(planets[i].x - planets[i + 1].x);
			edges.offer(new Edge(p1, p2, cost));
			edges.offer(new Edge(p2, p1, cost));
		}
		
		Arrays.sort(planets, (o1, o2) -> Integer.compare(o1.y, o2.y));
		for(int i = 0; i < N - 1; i++){
			int p1 = planets[i].num;
			int p2 = planets[i + 1].num;
			int cost = Math.abs(planets[i].y - planets[i + 1].y);
			edges.offer(new Edge(p1, p2, cost));
			edges.offer(new Edge(p2, p1, cost));
		}
		
		Arrays.sort(planets, (o1, o2) -> Integer.compare(o1.z, o2.z));
		for(int i = 0; i < N - 1; i++){
			int p1 = planets[i].num;
			int p2 = planets[i + 1].num;
			int cost = Math.abs(planets[i].z - planets[i + 1].z);
			edges.offer(new Edge(p1, p2, cost));
			edges.offer(new Edge(p2, p1, cost));
		}
		
		// 완료
		int count = 0;
		while(!edges.isEmpty()) {
			if(count == N - 1) break;
			Edge nowEdge = edges.poll();
			if(find(nowEdge.start) != find(nowEdge.end)) {
				union(nowEdge.start, nowEdge.end);
				result += nowEdge.cost;
				count++;
			}
		}
		
		System.out.println(result);
		
	}
	
	private static int find(int a) {
		if(planet[a] == a) return a;
		return planet[a] = find(planet[a]);
	}
	
	private static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return;
		else planet[parentB] = parentA;
	}
}
