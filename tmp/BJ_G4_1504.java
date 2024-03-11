/**

 @author 한규준
 @since 2023-08-09
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_1504.java
 @youtube
 @performance 72680KB, 800ms
 @category 다익스트라
 @note

1에서 N으로 이동하는데 V1, V2를 무조건 거쳐야 함.
1 -> V1 -> V2 -> N
1 -> V2 -> V1 -> N
두 방법 중 가장 작은 수를 출력
혹은 둘 다 integer max값이면 -1출력
(갈 방법이 없음)


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_1504 {
	private static int costToNV1V2 = 0;
	private static int costToNV2V1 = 0;
	private static ArrayList<ArrayList<Node>> graph;
	
	private static int N;
	private static int E;
	private static int V1;
	private static int V2;
	
	private static class Node{
		int idx, cost;
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	private static final int INF = 987654321;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(A).add(new Node(B, weight));
			graph.get(B).add(new Node(A, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());
		
		// 초기설정 완료
		// V1, V2를 모두 거쳐가는 경로만
		// 무조건 1번 -> N번 정점으로 이동하는데
		// V1, V2를 모두 거쳐야 한다.
		
		// 1. 1 -> N으로?
		// 2-1. 1 -> V1 -> V2 -> N
		// 2-2. 1 -> V2 -> V1 -> N
		// 2-1, 2-2비교
		
		int temp1, temp2, temp3;
		temp1 = dk(1, V1);
		temp2 = dk(V1, V2);
		temp3 = dk(V2, N);
		if(temp1 == Integer.MAX_VALUE || temp2 == Integer.MAX_VALUE || temp3 == Integer.MAX_VALUE) {
			costToNV1V2 = Integer.MAX_VALUE;
		} else {
			costToNV1V2 = temp1 + temp2 + temp3;
		}
		
		temp1 = dk(1, V2);
		temp2 = dk(V2, V1);
		temp3 = dk(V1, N);
		if(temp1 == Integer.MAX_VALUE || temp2 == Integer.MAX_VALUE || temp3 == Integer.MAX_VALUE) {
			costToNV2V1 = Integer.MAX_VALUE;
		} else {
			costToNV2V1 = temp1 + temp2 + temp3;
		}
		
		if(costToNV1V2 == costToNV2V1 && costToNV1V2 == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else System.out.println(Math.min(costToNV1V2, costToNV2V1));
	}
	
	// start부터 end까지의 거리 구하기
	private static int dk(int start, int end) {
		int[] distance = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		queue.offer(new Node(start, 0));
		distance[start] = 0;
		
		while(!queue.isEmpty()) {
			Node curNode = queue.poll();
			// 뽑았는데 이미 멀면 의미 X
			if(distance[curNode.idx] < curNode.cost) {
				continue;
			}
			
			// 뽑은 노드의 인접 노드를 검사한다.
			for(int i = 0; i < graph.get(curNode.idx).size(); i++) {
				Node nextNode = graph.get(curNode.idx).get(i);
				if(distance[nextNode.idx] > curNode.cost + nextNode.cost) {
					distance[nextNode.idx] = curNode.cost + nextNode.cost;
					queue.offer(new Node(nextNode.idx, distance[nextNode.idx]));
				}
			}
		}
		return distance[end];
	}
}
