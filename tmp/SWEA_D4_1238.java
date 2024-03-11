/**

 @author 한규준
 @since 2023-08-23
 @see 
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/SWEA_D4_1238.java
 @youtube
 @performance 20276kb, 122ms
 @category BFS
 @note

1. 간선을  모두 저장해야 하는데, 1~100중 랜덤으로 주어집니다.
2. 따라서 101크기의 ArrayList를 선언하고 이것을 노드로 삼습니다.
3. ArrayList에 Edge를 입력받아 하나씩 붙입니다.
4. 단방향 그래프 BFS를 진행하는데, visited에 depth을 표시하면 간단하게 깊이를 표시할 수 있습니다.
5. visited배열을 탐사하며, 값이 가장 큰 visited인덱스를 가져옵니다.
6. 이때, 오름차순으로 탐사하며 visited값이 같은 경우 visited인덱스만 교체합니다.

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

public class SWEA_D4_1238 {
	
	// 간선을 저장할 Edge클래스
	// 간선 시작노드, 간선 도달노드를 저장합니다.
	public static class Edge{
		int start;
		int end;
		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());

			// 입력데이터 사이즈, 시작노드를 저장합니다.
			int size = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			// ArrayList에 간선을 저장합니다.
			// 노드는 1~100까지의 수이고, 범위가 지정되어 있지 않습니다.
			// 1~100까지는 메모리를 크게 먹지 않기 때문에, 1~100까지 ArrayList를 미리 선언합니다.
			ArrayList<ArrayList<Edge>> edges = new ArrayList<>();
			for (int i = 0; i < 101; i++) {
				edges.add(new ArrayList<>());
			}

			// 간선을 저장합니다.
			// 데이터는 from-to 쌍으로 되어 있기 때문에, size/2로 한번에 두개씩 입력받습니다.
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < size / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edges.get(from).add(new Edge(from, to));
			}

			// BFS탐색을 진행할 Deque를 선언합니다.
			// depth확인 및 중복방문을 예방할 visited배열을 선언합니다.
			Deque<Integer> deq = new ArrayDeque<>();
			int[] visited = new int[101];
			
			// 첫 시작할 노드를 deq에 입력하고, depth를 표시합니다.
			deq.offer(start);
			visited[start] = 1;

			while (!deq.isEmpty()) {
				// deque가 빌 때까지 노드를 꺼내어 확인합니다.
				int nowNode = deq.poll();

				// 현재 노드의 모든 간선을 확인합니다.
				for (int i = 0; i < edges.get(nowNode).size(); i++) {
					Edge nowEdge = edges.get(nowNode).get(i);
					// 간선의 목적지가 미방문 노드라면 depth를 더하고 방문합니다.
					if (visited[nowEdge.end] == 0) {
						visited[nowEdge.end] = visited[nowEdge.start] + 1;
						deq.offer(nowEdge.end);
					}
				}
			}

			// value는 depth이고, index는 값입니다.
			// 따라서, value로 비교하고 index를 저장합니다.
			// value가 같다면, index만을 저장합니다.
			// 내림차순으로 진행하면 안되고, 오름차순으로 진행해야 합니다.
			int maxValue = 0;
			int maxIdx = 0;
			for (int i = 1; i < 101; i++) {
				if (maxValue <= visited[i]) {
					maxValue = visited[i];
					maxIdx = i;
				}
			}

			// 결과를 출력합니다.
			bw.write("#" + tc + " " + maxIdx + "\n");
		}
		bw.flush();
		bw.close();
	}
}
