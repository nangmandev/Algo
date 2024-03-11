/**

 @author 한규준
 @since 2023-08-11
 @see
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_13549.java
 @youtube
 @performance 17872KB, 136ms
 @category BFS
 @note

시작노드 중복방문하는 경우 생각하기
일반 BFS처리, 2배할때 카운트 0인거 생각해서 우선순위 정하기

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G5_13549 {
	int minValue = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int[] arr = new int[100001];
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deq = new ArrayDeque<>();
		deq.add(N);
		arr[N] = 1;
		
		while(!deq.isEmpty()) {
			int temp = deq.poll();
			//System.out.println(temp);
			if(temp == K) {
				System.out.println(arr[temp] - 1);
			}
			
			int[] next = {temp * 2, temp - 1, temp + 1};
			for(int i = 0; i < 3; i++) {
				if(next[i] >= 0
				&& next[i] <= 100000
				&& arr[next[i]] == 0) {
					deq.add(next[i]);
					if(i == 0)arr[next[i]] = arr[temp];
					else arr[next[i]] = arr[temp] + 1;		
				}
					
			}
		}
	}
}
