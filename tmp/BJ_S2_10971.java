/**

 @author 한규준
 @since 2023-08-30
 @see https://www.acmicpc.net/problem/10971
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_10971.java
 @youtube
 @performance 17140KB, 516ms
 @category 백트래킹, DFS
 @note

1. 무조건 순환이 가능한 경로가 주어집니다.
 2. 따라서, 출발점에서 시작하여 모든 노드를 통과하고 출발점으로 돌아오는 경로를 찾습니다.
 3. 또한 중복해서 방문하는 경우가 없어야 합니다.
 4. 노드에 들를 때마다 모든 간선을 확인하며, 이미 방문한 노드를 visited를 사용하여 재방문하지 않습니다.
 5. 이렇게 되면, 최종적으로 출발점에서 모든 노드를 들르고 다시 출발점으로 돌아오는 경로만 남습니다.
 6. 이 경로들의 거리를 비교합니다.
 7. 이때 모든 노드를 한 번씩 들르며 순회하므로, 어떤 노드에서 시작하든 같은 결과입니다.
 8. 따라서 반복문을 돌릴 필요 없이 한 번만 순회하면 됩니다.
9. 이 과정을 백트래킹, DFS로 수행합니다.

 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_S2_10971 {
	static int N;
	// 간선 정보를 저장하는 Edge클래스입니다.
	// ArrayList를 통해 출발노드를 관리하므로
	// 간선 클래스는 도착지점과 비용만을 가집니다.
	static class Edge{
		int end;
		int cost;
		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}
	// 노드들이 가지고 있는 간선을 관리하는 ArrayList입니다.
	static ArrayList<ArrayList<Edge>> edges;
	// 노드 재방문 여부를 체크하는 visited배열입니다.
	static boolean[] visited;
	// 다시 출발지점으로 돌아온 것을 확인하는 전역변수입니다.
	static int start;
	// 최소값 비교를 위한, 도달할 수 없는 수입니다.
	static int min = 1000000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		edges = new ArrayList<>();

		// ArrayList에 노드별로 모든 간선을 받고 저장합니다.
		for(int i = 0; i < N; i++) {
			edges.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 0) continue;
				else edges.get(i).add(new Edge(j, temp));
			}
		}

		// visited, start를 초기화하고 탐색을 시작합니다.
		visited = new boolean[N];
		start = 0;
		DFS(0, 0, 0);
		
		System.out.println(min);
	}
	
	private static void DFS(int city, int count, int costs) {
		// 출발지로 돌아올 때마다 전체 노드를 들렀는지 count로 확인합니다.
		// count는 depth가 1 내려갈 때마다 1 증가합니다.
		// 같은 노드를 두 번 들를 수 없기 때문에 N번 진행하면 출발 노드로 되돌아온 것입니다.
		// -> 출발지점은 visited로 처리하지 않고 DFS초기값으로만 주어집니다.
		if(city == start) {
			if(count == N) min = Math.min(min, costs);
		}

		// 현재 노드마다 모든 간선을 꺼내서 방문했는지 확인합니다.
		// 미방문 노드이면, 다음 그 간선이 향하는 노드를 확인합니다.
		for(Edge nowEdge : edges.get(city)) {
			if(!visited[nowEdge.end]) {
				visited[nowEdge.end] = true;
				DFS(nowEdge.end, count + 1, costs + nowEdge.cost);
				visited[nowEdge.end] = false;
			}
		}
	}
}