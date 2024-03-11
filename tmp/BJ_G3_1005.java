/**

 @author 한규준
 @since 2023-09-15
 @see https://www.acmicpc.net/problem/1005
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G3_1005.java
 @youtube
 @performance 242576KB, 1428ms
 @category 위상정렬, DP
 @note

 애매한 DP
 배열사용시 시간 오래걸림(N^2)
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ_G3_1005 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] D = new int[N + 1];
			int[] sum = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				D[i] = Integer.parseInt(st.nextToken());
				sum[i] = D[i];
			}

			int[][] depends = new int[N + 1][N + 1];
			int[] level = new int[N + 1];
			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				depends[a][b] = 1;
				level[b]++;
			}
			
			Deque<Integer> deq = new ArrayDeque<>();
			for(int i = 1; i <= N; i++) {
				if(level[i] == 0) {
					deq.offer(i);
				}
			}
			
			int dest = Integer.parseInt(br.readLine());
			
			while(!deq.isEmpty()) {
				int temp = deq.poll();
				for(int i = 1; i <= N; i++) {
					if(depends[temp][i] != 0 && level[i] != 0) {
						sum[i] = Math.max(sum[i], sum[temp] + D[i]);
						if(--level[i] == 0) {
							deq.offer(i);
						}
					}
				}
				if(temp == dest) break;
			}
			
			bw.write(sum[dest] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
