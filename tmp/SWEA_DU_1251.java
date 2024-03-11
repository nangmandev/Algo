package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_DU_1251 {
	public static class Island{
		int num;
		int y;
		int x;
		public Island(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
	
	public static class Edge implements Comparable<Edge>{
		int start;
		int end;
		double cost;
		public Edge(int start, int end, double cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st1, st2;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			double result = 0.0;
			int N = Integer.parseInt(br.readLine());
			
			Island[] islands = new Island[N];
			int[] roots = new int[N];
			
			for(int i = 0; i < N; i++) {
				roots[i] = i;
			}
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				islands[i] = new Island(Integer.parseInt(st2.nextToken()), Integer.parseInt(st1.nextToken()), i);
			}
			
			double E = Double.parseDouble(br.readLine());
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) continue;
					double cost = Math.sqrt(Math.pow(Math.abs(islands[i].x - islands[j].x), 2) + Math.pow(Math.abs(islands[i].y - islands[j].y), 2));
					pq.offer(new Edge(islands[i].num, islands[j].num, cost));
				}
			}
			
			int count = 0;
			while(!pq.isEmpty()) {
				Edge nowEdge = pq.poll();
				if(union(roots, nowEdge.start, nowEdge.end)) {
					result += (E * Math.pow(nowEdge.cost, 2));
					count++;
					continue;
				}
				if(count == N - 1) break;
			}
			
			System.out.printf("#" + tc + " " + (long)Math.round(result) + "\n");
		}
	}
	
	private static int find(int[] roots, int a) {
		if(roots[a] == a) return a;
		return roots[a] = find(roots, roots[a]);
	}
	
	private static boolean union(int[] roots, int a, int b) {
		int pA = find(roots, a);
		int pB = find(roots, b);
		if(pA == pB) return false;
		else roots[pB] = pA;
		return true;
	}
}
