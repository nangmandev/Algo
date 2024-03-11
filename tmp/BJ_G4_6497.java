/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/6497
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_6497.java
 @youtube
 @performance 243600KB, 792ms
 @category MST
 @note

1. 원래 총 간선의 가중치를 구한다.
2. MST를 만들어 MST의 간선 가중치를 구한다.
3. 총 간선 - MST간선 을 출력한다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_6497 {
	public static int endFlag = 0;
	public static int currentCost;
	public static Edge[] edges;
	public static int[] nodes;
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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if(m == 0 && n == 0) break;
			currentCost = 0;
			nodes = new int[m];
			edges = new Edge[n];
		
			for(int i = 0; i < m; i++) {
				nodes[i] = i;
			}
		
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				currentCost += c;
				edges[i] = new Edge(a, b, c);
			}
		
			Arrays.sort(edges);
		
			int countEdge = 0;
			int newCost = 0;
		
			for(int i = 0; i < edges.length; i++) {
				if(countEdge == m - 1) break;
			
				Edge nowEdge = edges[i];
				if(find(nowEdge.start) == find(nowEdge.end)) {
					continue;
				}
				else {
					newCost += nowEdge.cost;
					union(nowEdge.start, nowEdge.end);
					countEdge++;
				}
			}
		
			bw.write(currentCost - newCost + "\n");
		}
		bw.flush();
		bw.close();
	}
	
	private static int find(int a) {
		if(nodes[a] == a) return a;
		return nodes[a] = find(nodes[a]);
	}
	
	private static boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA == parentB) return false;
		else nodes[parentB] = parentA;
		return true;
	}
}
