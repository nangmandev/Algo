/**

 @author 한규준
 @since 2023-09-06
 @see https://www.acmicpc.net/problem/11725
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_11725.java
 @youtube
 @performance 7298KB, 652ms
 @category 트리 부모, BFS
 @note

그래프 만들기
1을 시작으로 BFS, 기록

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

public class BJ_S2_11725 {
	static ArrayList<ArrayList<Integer>> edges;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		edges = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
		}
		
		int[] par = new int[N + 1];
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(b);
			edges.get(b).add(a);
		}
		
		// 초기화 끝
		// BFS로 탐색
		
		Deque<Integer> deq = new ArrayDeque<>();
		deq.add(1);
		
		while(!deq.isEmpty()) {
			int nowNode = deq.poll();
			
			for(int i = 0; i < edges.get(nowNode).size(); i++) {
				int nextNode = edges.get(nowNode).get(i);
				if(par[nextNode] == 0) {
					par[nextNode] = nowNode;
					deq.add(nextNode);
				}
			}
		}
		
		for(int i = 2; i <= N; i++) {
			bw.write(par[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}