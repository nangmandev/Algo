/**

 @author 한규준
 @since 2023-08-14
 @see https://www.acmicpc.net/problem/7662
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G4_7662.java
 @youtube
 @performance 456700KB, 3536ms
 @category 맵, 우선순위 큐
 @note



 */

package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_G4_7662 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			int k = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
			PriorityQueue<Integer> rpq = new PriorityQueue<>();
			Map<Integer, Integer> map = new HashMap<>();
			
			for(int j = 0; j < k; j++) {
				st = new StringTokenizer(br.readLine());
				char order = st.nextToken().charAt(0);
				int insert = Integer.parseInt(st.nextToken());
				
				if(order == 'I') {
					pq.offer(insert);
					rpq.offer(insert);
					if(map.containsKey(insert)) {
						map.put(insert, map.get(insert) + 1);
					}
					else map.put(insert, 1);
				}
				else {
					if(map.isEmpty()) {
						pq.clear();
						rpq.clear();
						continue;
					}
					if(insert == 1) {
						while(true) {
							if(pq.isEmpty()) {
								break;
							}
							int t = pq.poll();
							if(map.containsKey(t)) {
								if(map.get(t) == 1) {
									map.remove(t);
								}
								else {
									map.put(t, map.get(t) - 1);
								}
								break;
							}
						}
					}
					else {
						while(true) {
							if(rpq.isEmpty()) {
								break;
							}
							int t = rpq.poll();
							if(map.containsKey(t)) {
								if(map.get(t) == 1) {
									map.remove(t);
								}
								else {
									map.put(t, map.get(t) - 1);
								}
								break;
							}
						}
					}
				}
			}
			
			if(map.isEmpty()) System.out.println("EMPTY");
			else {
				int max = 0, min = 0;
				while(true) {
					int t = pq.poll();
					if(map.containsKey(t)) {
						max = t;
						break;
					}
				}
				while(true) {
					int t = rpq.poll();
					if(map.containsKey(t)) {
						min = t;
						break;
					}
				}
				System.out.println(max + " " + min);
			}
		}
		
		
	}
}
