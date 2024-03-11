package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G5_22234 {
	private static class Client{
		int p;
		int t;
		public Client(int p, int t) {
			this.p = p;
			this.t = t;
		}
		public void minusT() {
			this.t -= 1;
		}
	}
	
	private static class NewClient{
		int p;
		int t;
		int c;
		public NewClient(int p, int t, int c) {
			this.p = p;
			this.t = t;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		Deque<Client> deque = new ArrayDeque<>();
		PriorityQueue<NewClient> priorityQueue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.c, o2.c));
		
		int p, t, c;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			deque.add(new Client(p, t));
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			priorityQueue.add(new NewClient(p, t, c));
		}
		
		// 초기화 끝
		
		int secondCheck = 0;
		int clientTimeCheck = 0;
		Client nowClient = deque.poll();
		
		while(secondCheck < W) {
			if(!priorityQueue.isEmpty()) {
				NewClient nc = priorityQueue.poll();
				if(nc.c == secondCheck) {
					deque.addLast(new Client(nc.p, nc.t));
				} else {
					priorityQueue.add(nc);
				}
			}
			// 새 손님 체크
			
			if(clientTimeCheck == T && nowClient.t != 0) {
				deque.add(nowClient);
				nowClient = deque.poll();
				clientTimeCheck = 0;
			} else if(clientTimeCheck == T && nowClient.t == 0) {
				nowClient = deque.poll();
				clientTimeCheck = 0;
			}else if(nowClient.t == 0) {
				nowClient = deque.poll();
				clientTimeCheck = 0;
			}
			bw.write(nowClient.p + "\n");
			nowClient.minusT();
			secondCheck++;
			clientTimeCheck++;
		}
		
		bw.flush();
		bw.close();
	}
}
