/**

 @author 한규준
 @since 2023-0811
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1238.java
 @youtube
 @performance 115508KB, 680ms
 @category 다익스트라
 @note

다익스트라 2회

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

public class BJ_G3_1238 {
	static int N;
	static int M;
	static int X;
	static ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
	static int[] result;
	static int maxValue = 0;
	
	static int INF = 987654321;
	
	public static class Node{
		int idx;
		int weight;
		public Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		result = new int[N + 1];
		
		for(int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			nodes.get(a).add(new Node(b, t));
		}
		
		for(int i = 1; i <= N; i++) {
			if(i != X) {
				result[i] = dijkstra(i, X) + dijkstra(X, i);
				if(result[i] > maxValue) maxValue = result[i];
			}
		}
		System.out.println(maxValue);
	}
	
	public static int dijkstra(int start, int end) {
		int[] distance = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			distance[i] = INF;
		}
		Deque<Node> deq = new ArrayDeque<>();
		deq.add(new Node(start, 0));
		distance[start] = 0;
		
		while(!deq.isEmpty()) {
			Node nowNode = deq.poll();
			if(distance[nowNode.idx] < nowNode.weight) {
				continue;
			}
			
			for(int i = 0; i < nodes.get(nowNode.idx).size(); i++) {
				Node nextNode = nodes.get(nowNode.idx).get(i);
				if(distance[nextNode.idx] > nowNode.weight + nextNode.weight) {
					distance[nextNode.idx] = nowNode.weight + nextNode.weight;
					deq.add(new Node(nextNode.idx, distance[nextNode.idx]));
				}
			}
		}
		return distance[end];
	}
}
