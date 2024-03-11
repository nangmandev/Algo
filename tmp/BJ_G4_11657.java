/**

 @author 한규준
 @since 2023-08-10
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_11657.java
 @youtube
 @performance 17208KB, 212ms
 @category 벨만포드
 @note

1. 벨만 포드 그대로 구현
2. 루프도는구간에서 최대cost가 int, long범위 넘는지 생각해볼것

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_G4_11657 {
	static int N;
	static int M;
	static int[][] edges;
	static long[] distance;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// N개의 도시
		// M개의 버스
		// A시작, B도착, C시간
		// C양수, C0(순간이동), C음수(되돌아가는경우)
		// 1번 도시에서 출발, 나머지 도시로 가는 가장 빠른 시간
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new int[M + 1][3];
		distance = new long[N + 1];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i][0] = Integer.parseInt(st.nextToken());
			edges[i][1] = Integer.parseInt(st.nextToken());
			edges[i][2] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			distance[i] = INF;
		}
		
		// 초기화 완료
		
		boolean tf = Bellman_Ford(1);
		
		if(tf) {
			bw.write(-1 + "\n");
		} else {
			for(int i = 2; i <= N; i++) {
				if(distance[i] == INF) bw.write(-1 + "\n");
				else bw.write(distance[i] + "\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	private static boolean Bellman_Ford(int start) {
		distance[start] = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				int nowNode = edges[j][0];
				int nextNode = edges[j][1];
				int cost = edges[j][2];
				
				if(distance[nowNode] != INF && distance[nextNode] > distance[nowNode] + cost) {
					distance[nextNode] = distance[nowNode] + cost;
					if(i == N) return true;
				}
			}
		}
		return false;
	}
}
