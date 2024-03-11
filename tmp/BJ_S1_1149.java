/**

 @author 한규준
 @since 2023-08-29
 @see https://www.acmicpc.net/problem/1149
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_S1_1149.java
 @youtube
 @performance 12160KB, 88ms
 @category DP
 @note

1. 완전탐색을 실시하려면 O(N^3)입니다. -> 시간초과 발생
 2. 문제를 부분으로 나눌 수 있습니다. -> 지금 색깔이 정해지면, 다음 색깔의 경우의 수도 정해짐
 3. 부분마다 결정한 것이 최적해입니다. -> 각 부분마다 가장 최적의 수를 결정합니다. 다른 부분 탐색은 필요하지 않습니다.
 4. 따라서, 처음 3가지 색의 경우를 정하고 각각의 상황마다 최적의 해를 찾아갑니다.
 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_S1_1149 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] status = new int[N][3];
		
		st = new StringTokenizer(br.readLine());
		status[0][0] = Integer.parseInt(st.nextToken());
		status[0][1] = Integer.parseInt(st.nextToken());
		status[0][2] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			int blue = Integer.parseInt(st.nextToken());
			status[i][0] += Math.min(status[i - 1][1], status[i - 1][2]) + red;
			status[i][1] += Math.min(status[i - 1][0], status[i - 1][2]) + green;
			status[i][2] += Math.min(status[i - 1][0], status[i - 1][1]) + blue;
		}
		
		bw.write(Math.min(Math.min(status[N - 1][0], status[N - 1][1]), status[N - 1][2]) + "\n");
		bw.flush();
		bw.close();
	}
}
