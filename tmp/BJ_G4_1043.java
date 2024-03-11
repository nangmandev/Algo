package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class BJ_G4_1043 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] visited = new int[N];
		int[] parties = new int[M];
		int[][] arr = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람들 진실의방에 추가
		Deque<Integer> deq = new ArrayDeque<>();
		for(int i = 0; i < k; i++) {
			int temp = Integer.parseInt(st.nextToken()) - 1;
			deq.offer(temp);
			visited[temp] = 1;
		}
		
		// 사람 -> 파티 테이블 초기화
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			for(int j = 0; j < p; j++) {
				int temp = Integer.parseInt(st.nextToken()) - 1;
				arr[temp][i] = 1;
			}
		}
		
		// 큐가 빌때까지 진행
		while(!deq.isEmpty()) {
			int nowPerson = deq.poll();
			//System.out.println(nowPerson);
			for(int i = 0; i < M; i++) {
				if(arr[nowPerson][i] == 1) {
					parties[i] = 1;
					for(int j = 0; j < N; j++) {
						if(arr[j][i] == 1 && visited[j] == 0) {
							deq.offer(j);
							visited[j] = 1;
						}
					}
				}
			}
		}
		
		int count = 0;
		for(int i = 0; i < M; i++) {
			if(parties[i] == 0) count++;
		}
		
		bw.write(count + "\n");
		bw.flush();
		bw.close();
	}
}