/**

 @author 한규준
 @since 2023-10-05
 @see https://www.acmicpc.net/problem/4485
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_4485.java
 @youtube
 @performance 25652KB, 244ms
 @category 다익스트라
 @note

그래프 전체 노드를 다익스트라 노드로 때려박기

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_4485 {
	static class Edge implements Comparable<Edge> {
		int to;
		int cost;

		public Edge(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	static final int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int tc = 0;
		while (true) {
			tc++;
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
			int[] distance = new int[N * N];
			for (int i = 0; i < N * N; i++) {
				edges.add(new ArrayList<>());
				distance[i] = INF;
			}

			int[] tmp = new int[N * N];
			int count = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					tmp[count++] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N * N; i++) {
				if (i % N != 0)
					edges.get(i).add(new Edge(i - 1, tmp[i - 1]));
				if (i % N != N - 1)
					edges.get(i).add(new Edge(i + 1, tmp[i + 1]));
				if (i >= N)
					edges.get(i).add(new Edge(i - N, tmp[i - N]));
				if (i < (N * (N - 1)))
					edges.get(i).add(new Edge(i + N, tmp[i + N]));
			}

			// 비용 : tmp[i]

			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.offer(new Edge(0, tmp[0]));
			distance[0] = tmp[0];

			while (!pq.isEmpty()) {
				Edge nowEdge = pq.poll();

				for (int i = 0; i < edges.get(nowEdge.to).size(); i++) {
					Edge nextEdge = edges.get(nowEdge.to).get(i);
					if (distance[nextEdge.to] > nowEdge.cost + nextEdge.cost) {
						distance[nextEdge.to] = nowEdge.cost + nextEdge.cost;
						pq.offer(new Edge(nextEdge.to, distance[nextEdge.to]));
					}
				}
			}

			bw.write("Problem " + tc + ": " + distance[N * N - 1] +"\n");
		}
		bw.flush();
		bw.close();
	}
}
