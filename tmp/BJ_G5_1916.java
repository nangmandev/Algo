/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_1916.java
 @youtube
 @performance 59396KB, 624ms
 @category 다익스트라
 @note

1. 다익스트라로 start노드에서 모든 노드까지의 최단거리를 구하고
 2. end노드에 대한 최단거리를 출력하면 됩니다.
 다익스트라 연습할때 모범적인 문제

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.tree.ExpandVetoException;

public class BJ_G5_1916 {
	private static class Node{
		int idx;
		int cost;
		public Node(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Node>> roads = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			roads.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			roads.get(start).add(new Node(dest, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// 초기화 완료
		
		int[] distance = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		nodes.add(new Node(start, 0));
		distance[start] = 0;
		
		while(!nodes.isEmpty()) {
			Node nowNode = nodes.poll();
			if(distance[nowNode.idx] < nowNode.cost) continue;
			
			for(int i = 0; i < roads.get(nowNode.idx).size(); i++) {
				Node nextNode = roads.get(nowNode.idx).get(i);
				if(distance[nextNode.idx] > nowNode.cost + nextNode.cost) {
					distance[nextNode.idx] = nowNode.cost + nextNode.cost;
					nodes.add(new Node(nextNode.idx, distance[nextNode.idx]));
				}
			}
		}
		System.out.println(distance[end]);
	}
}
