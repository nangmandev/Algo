/**

 @author 한규준
 @since 2023-10-04
 @see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWXQsLWKd5cDFAUo&probBoxId=AYr3k03KgA4DFAV6&type=PROBLEM&problemBoxTitle=1004%EC%A3%BC&problemBoxCnt=2
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_키순서.java
 @youtube
 @performance 94396KB, 1229ms
 @category BFS
 @note

키 순서를 비교하기 위해서는 결국 자기자신을 중심으로 모든 사람과 비교가 가능해야 합니다.
즉, 자기 자신보다 작은 사람 + 자기 자신보다 큰 사람 = N - 1명이 되어야 합니다.
그래프 탐색을 진행할 때 방문하는 노드는 자기 자신보다 키가 큰 사람입니다.
즉, 자기자신 : 방문하는 노드보다 키가 작음
방문하는 노드 : 자기 자신보다 키가 큼
이므로 노드를 방문할 때마다 확정된 관계(작고 큼)에 따라 자기 자신과 방문하는 노드를 카운트합니다.
마지막에 노드를 돌면서 카운트가 N - 1이 되어 있는 노드는 자신의 키 순서를 정확히 알 수 있습니다.

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

public class SWEA_D4_키순서 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());

			ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

			for (int i = 0; i <= N; i++) {
				edges.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edges.get(from).add(to);
			}

			int[] students = new int[N + 1];
			Deque<Integer> deq = new ArrayDeque<>();

			boolean[] visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				deq.offer(i);

				for (int k = 1; k <= N; k++) {
					visited[k] = false;
				}
				while (!deq.isEmpty()) {
					int nowNode = deq.poll();

					for (int j = 0; j < edges.get(nowNode).size(); j++) {
						if (!visited[edges.get(nowNode).get(j)]) {
							visited[edges.get(nowNode).get(j)] = true;
							students[i]++;
							students[edges.get(nowNode).get(j)]++;
							deq.offer(edges.get(nowNode).get(j));
						}
					}
				}
			}

			int result = 0;
			for (int i = 1; i <= N; i++) {
				if (students[i] == N - 1) {
					result++;
				}
			}

			bw.write("#" + t + " " + result + "\n");
		}
		bw.flush();
		bw.close();
	}
}
