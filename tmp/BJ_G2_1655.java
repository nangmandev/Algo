/**

 @author 한규준
 @since 2023-08-25
 @see https://www.acmicpc.net/problem/1655
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G2_1655.java
 @youtube
 @performance 41612KB, 632ms
 @category 자료구조
 @note

계속 양쪽 우선순위 큐의 크기를 같게 해야 한다.
왼쪽 우선순위 큐는 내림차순, 오른쪽 우선순위 큐는 오름차순
-> 무조건 값을 오른쪽 우선순위 큐에 넣고, 2개를 받을 때마다 1개를 왼쪽으로 넘긴다.
-> 1개를 받았는데 최소값일 수 있다. 이럴 때는 왼쪽과 비교해서 작으면 바꿔준다.
-> 둘 중 작은 값을 출력하면 된다.

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G2_1655 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pqBig = new PriorityQueue<>();
		PriorityQueue<Integer> pqSmall = new PriorityQueue<>((o1, o2) -> Integer.compare(o1, o2) * -1);
		
		int temp;
		for(int i = 0; i < N; i++) {
			temp = Integer.parseInt(br.readLine());
			if(i == 0) {
				pqSmall.offer(temp);
				bw.write(pqSmall.peek() + "\n");
				continue;
			}
			pqBig.offer(temp);
			if(pqBig.peek() < pqSmall.peek()) {
				temp = pqSmall.poll();
				pqSmall.offer(pqBig.poll());
				pqBig.offer(temp);
			}
			if(i % 2 == 0) pqSmall.offer(pqBig.poll());
			bw.write(Math.min(pqSmall.peek(), pqBig.peek()) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
