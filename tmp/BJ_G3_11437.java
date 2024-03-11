/**

 @author 한규준
 @since 2023-09-15
 @see https://www.acmicpc.net/problem/11437
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_11437.java
 @youtube
 @performance 48716KB, 2604ms
 @category 트리, LCA, DFS
 @note

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_G3_11437 {
	static int N;
	static ArrayList<ArrayList<Integer>> roads;
	static boolean[] visited;
	static Node[] nodes;

	static class Node {
		int level;
		int u;

		public Node(int level, int u) {
			this.level = level;
			this.u = u;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		nodes = new Node[N + 1];
		visited = new boolean[N + 1];
		roads = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			roads.add(new ArrayList<>());
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			roads.get(u).add(d);
			roads.get(d).add(u);
		}

		visited[1] = true;
		nodes[1] = new Node(1, 0);
		makeTree(1, 1);

		int tc = Integer.parseInt(br.readLine());

		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (nodes[b].level > nodes[a].level) {
				int temp = a;
				a = b;
				b = temp;
			}

			while (true) {
				if (nodes[a].level == nodes[b].level) {
					break;
				} else {
					a = nodes[a].u;
				}
			}

			while (true) {
				if (a == b)
					break;
				else {
					a = nodes[a].u;
					b = nodes[b].u;
				}
			}

			bw.write(a + "\n");
		}

		bw.flush();
		bw.close();
	}

	private static void makeTree(int node, int depth) {
		for (int i = 0; i < roads.get(node).size(); i++) {
			int temp = roads.get(node).get(i);
			if (!visited[temp]) {
				visited[temp] = true;
				nodes[temp] = new Node(depth + 1, node);
				makeTree(temp, depth + 1);
			}
		}
	}
}