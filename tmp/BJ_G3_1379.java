/**

 @author 한규준
 @since 2023-08-15
 @see https://www.acmicpc.net/problem/1379
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1379.java
 @youtube
 @performance 71964KB, 720ms
 @category 우선순위 큐
 @note

전부 다 탐색할 것인가?
 아니면 특정 자료 순으로만 찾을 것인가?

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G3_1379 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Sub> firstQueue = new PriorityQueue<>((o1, o2) -> o1.start != o2.start ? o1.start - o2.start : o1.end - o2.end);
		PriorityQueue<Sub> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.end != o2.end ? o1.end - o2.end : o1.start - o2.start);
		PriorityQueue<Sub> endQueue = new PriorityQueue<>((o1, o2) -> o1.num - o2.num);

		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			Sub r = new Sub(a, b, c);

			endQueue.add(r);
			firstQueue.add(r);
		}

		for(int i = 0; i < N; i++){
			Sub r = firstQueue.poll();
			if(priorityQueue.isEmpty()){
				priorityQueue.add(r);
				r.lec = priorityQueue.size();
			} else {
				Sub temp = priorityQueue.poll();
				if(temp.end <= r.start){
					r.lec = temp.lec;
					priorityQueue.add(r);
				}
				else {
					priorityQueue.add(temp);
					priorityQueue.add(r);
					r.lec = priorityQueue.size();
				}
			}
		}

		bw.write(priorityQueue.size() + "\n");

		for(int i = 0; i < N; i++){
			bw.write(endQueue.poll().lec + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}

class Sub{
	int num;
	int start;
	int end;
	int lec;
	
	public Sub(int num, int start, int end) {
		this.num = num;
		this.start = start;
		this.end = end;
	}
}