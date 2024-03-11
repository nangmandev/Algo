/**

 @author 한규준
 @since 2023-08-23
 @see https://www.acmicpc.net/problem/17471
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_17471.java
 @youtube
 @performance 14124KB, 96ms
 @category 구현, 그래프탐색, 조합
 @note

조합, 반대조합을 구하고
그래프 탐색으로 전부 연결되었는지 확인하고
최저차 구하기

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G4_17471 {
	
	static class Node{
		int num;
		int pop;
		public Node(int num, int pop) {
			this.num = num;
			this.pop = pop;
		}
	}
	
	static class Edge{
		int start;
		int end;
		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static int[] nodeNum;
	public static Node[] nodes;
	public static ArrayList<ArrayList<Edge>> edges;
	
	public static int minCount = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		nodeNum = new int[N];
		edges = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nodes[i] = new Node(i, Integer.parseInt(st.nextToken()));
			edges.add(new ArrayList<>());
			nodeNum[i] = i;
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j = 0; j < size; j++) {
				edges.get(i).add(new Edge(i, Integer.parseInt(st.nextToken()) - 1));
			}
		}
		
		// 초기화 끝
		
		for(int i = 1; i <= N / 2; i++) {
			combi(0, new int[i], 0, i);
		}
		
		if(minCount == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minCount);
		
		
	}
	
	// 조합구하기
	private static void combi(int nth, int[] tempArr, int start, int len) {
		if(nth == tempArr.length) {
			//System.out.println(Arrays.toString(tempArr));
			if(checkArr(tempArr)) revCombi(tempArr, new int[nodeNum.length - len]);
			return;
		}
		for(int i = start; i < nodes.length; i++) {
			tempArr[nth] = nodeNum[i];
			combi(nth + 1, tempArr, i + 1, len);
		}
	}
	
	// 역조합구하기
	private static void revCombi(int[] originArr, int[] rtnArr) {
		int idx = 0;
		for(int i = 0; i < nodeNum.length; i++) {
			int flag = 0;
			for(int j = 0; j < originArr.length; j++) {
				if(i == originArr[j]) flag = 1;
			}
			if(flag == 0) rtnArr[idx++] = i;
		}
		

		// 조합/반대조합 구하고
		
		if(!checkArr(rtnArr)) return;
		
//		System.out.println(Arrays.toString(originArr));
//		System.out.println(Arrays.toString(rtnArr));

		int oneSide = 0;
		int otherSide = 0;
		
		for(int i = 0; i < originArr.length; i++) {
			oneSide += nodes[originArr[i]].pop;
		}
		for(int i = 0; i < rtnArr.length; i++) {
			otherSide += nodes[rtnArr[i]].pop;
		}
		
		int temp = Math.abs(oneSide - otherSide);
		
		minCount = Math.min(minCount, temp);
	}
	
	private static boolean checkArr(int[] arr) {
		// 조합의 어떤 원소는
		// 조합의 어떠한 나머지 원수 중 하나가 인접해야 한다.
		int tempArr[] = new int[arr.length];
		for(int i = 0; i < arr.length; i++) {
			tempArr[i] = arr[i];
		}
		Deque<Node> deq = new ArrayDeque<>();
		deq.offer(nodes[tempArr[0]]);
		tempArr[0] = -1;
		
		while(!deq.isEmpty()) {
			Node nowNode = deq.poll();
			
			for(int i = 0; i < edges.get(nowNode.num).size(); i++) {
				Edge nowEdge = edges.get(nowNode.num).get(i);
				for(int j = 0; j < tempArr.length; j++) {
					if(nowEdge.end == tempArr[j]) {
						deq.offer(nodes[tempArr[j]]);
						tempArr[j] = -1;
					}
				}
			}
		}
		for(int i = 0; i < tempArr.length; i++) {
			if(tempArr[i] != -1) {
				return false;
			}
		}
		return true;
	}
}
