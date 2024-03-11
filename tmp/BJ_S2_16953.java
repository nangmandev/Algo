/**

 @author 한규준
 @since 2023-09-04
 @see https://www.acmicpc.net/problem/16953
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S2_16953.java
 @youtube
 @performance 14124KB, 104ms
 @category BFS
 @note


 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_S2_16953 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		long res = -1;
		
		Deque<long[]> deq = new ArrayDeque<>();
		deq.add(new long[] {A, 0});
		
		while(!deq.isEmpty()) {
			long[] now = deq.poll();
			if(now[0] == B) {
				res = now[1] + 1;
				break;
			}
			
			if(now[0] * 2 <= B) {
				deq.add(new long[] {now[0] * 2, now[1] + 1});
			}
			if(now[0] * 10 + 1 <= B) {
				deq.add(new long[] {now[0] * 10 + 1, now[1] + 1});
			}
		}
		
		System.out.println(res);
	}
}
