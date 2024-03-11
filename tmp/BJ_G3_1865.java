/**

 @author 한규준
 @since 2023-08-10
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1865.java
 @youtube
 @performance 25848KB, 588ms
 @category 벨만포드
 @note

 1. 벨만 포드 그대로 구현
 2. 문제가 이상한데, 결국 모든 노드를 돌면서 한번이라도 루프 발생하면 YES

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G3_1865 {
	static int N, M, W;
	static int[][] edges;
	static long[] distance;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N개의 지점
		// M개의 도로(방향 X)
		// W개의 웜홀(방향 O)
		// 웜홀은 시간이 거꾸로간다.
		// 출발점 -> 도착점 -> 출발점일때, 시간이 되돌아가있는 경우
		int tc = Integer.parseInt(br.readLine());
		
		for(int TC = 1; TC <= tc; TC++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			edges = new int[M * 2 + W][3];

			for(int i = 0; i < M * 2; i += 2){
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				edges[i][0] = S;
				edges[i][1] = E;
				edges[i][2] = T;
				edges[i + 1][0] = E;
				edges[i + 1][1] = S;
				edges[i + 1][2] = T;
			}

			for(int i = M * 2; i < M * 2 + W; i++){
				st = new StringTokenizer(br.readLine());
				edges[i][0] = Integer.parseInt(st.nextToken());
				edges[i][1] = Integer.parseInt(st.nextToken());
				edges[i][2] = -Integer.parseInt(st.nextToken());
			}

			// 초기화 완료

			int flag = 1;
			for(int i = 1; i <= N; i++) {
				if(Bellman_Ford(i)) {
					flag = 0;
					break;
				}
			}

			if(flag == 1) System.out.println("NO");
			else System.out.println("YES");
		}
	}

	private static boolean Bellman_Ford(int start){
		distance = new long[N + 1];
		for(int i = 0; i <= N; i++){
			distance[i] = INF;
		}
		distance[start] = 0;
		boolean check;
		for(int i = 1; i <= N; i++){
			check = false;
			for (int j = 0; j < edges.length; j++) {
				int nowNode = edges[j][0];
				int nextNode = edges[j][1];
				int cost = edges[j][2];

				if (distance[nowNode] != INF && distance[nextNode] > distance[nowNode] + cost) {
					distance[nextNode] = distance[nowNode] + cost;
					check = true;
					if (i == N) return true;
				}
			}
			if(!check) break;
		}
		return false;
	}
}
