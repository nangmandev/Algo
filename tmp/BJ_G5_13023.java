/**

 @author 한규준
 @since 2023-08-22
 @see https://www.acmicpc.net/problem/13023
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_13023.java
 @youtube
 @performance 29300KB, 252ms
 @category 
 @note

depth가 4 이상이면 true
시작 노드를 전부 시도해보고
방문 배열 초기화 잘하고

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G5_13023 {
	static int N, M;
	static ArrayList<ArrayList<Edge>> edges;
	static int[] visited;
	static int flag;
	
	static class Edge{
		int start;
		int end;
		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			edges.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(new Edge(a, b));
			edges.get(b).add(new Edge(b, a));
		}
		
		for(int i = 0; i < N; i++) {
			visited = new int[N];
			visited[i] = 1;
			DFS(i, 0);
		}
		
		System.out.println(flag);
	}
	
	private static void DFS(int node, int level) {
		if(level == 4 || flag == 1) {
			flag = 1;
			return;
		}
		
		for(int i = 0; i < edges.get(node).size(); i++) {
			Edge nowEdge = edges.get(node).get(i);
			if(visited[nowEdge.end] == 0) {
				visited[nowEdge.end] = 1;
				DFS(nowEdge.end, level + 1);
				visited[nowEdge.end] = 0;
			}
		}
	}
}
